package backend.receiver;

import backend.exceptions.InvalidSelectionException;

public class MiniEditorImpl implements MiniEditor{

	private State state;
	CareTaker careTaker;
	int counter;
	int count;
	
	public MiniEditorImpl() {
		state = new State();
		careTaker = new CareTaker();
		careTaker.add(saveStateToMemento(),count);
	}
	
	public void setState(State state){
       this.state = state;
    }

    public State getState(){
       return state;
    }

    public Memento saveStateToMemento(){
	    return new Memento(new State(state));
    }

    public void getStateFromMemento(Memento Memento){
    	state = new State(Memento.getState());
    } 
	
	@Override
	public String getBuffer() {
		return state.getBufferContent();
	}

	@Override
	public String getSelection() {
		return state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()) + "[" + state.getSelectionStart() + "," + state.getSelectionEnd() + "]";
	}

	@Override
	public String getClipboard() {
		return state.getClipboard().getContents();
	}

	@Override
	public void editorInsert(String substring) {
		state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + substring + state.getBufferContent().substring(state.getSelectionEnd()));
		int value = state.getSelectionStart() + substring.length();
		state.setSelectionStart(value);
		state.setSelectionEnd(value);
		count = ++counter;
		careTaker.add(saveStateToMemento(), count);
	}

	@Override
	public void editorSelect(int start, int stop) {
		try {
			if((start >= 0 && start <= state.getBufferContent().length()) && (stop >=0 && stop  <= state.getBufferContent().length()) && (stop >= start)) {
				state.setSelectionStart(start);
				state.setSelectionEnd(stop);
				count = ++counter;
				careTaker.add(saveStateToMemento(), count);
			} else {
				throw new InvalidSelectionException();
			}
		} catch(InvalidSelectionException ise) {
			System.out.println("ERROR! Invalid selection.");
		}
	}

	@Override
	public void editorCopy() {
		if(state.getSelectionStart() != state.getSelectionEnd()) {
			state.getClipboard().setContents(state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()));
			count = ++counter;
			careTaker.add(saveStateToMemento(), count);
		}
	}

	@Override
	public void editorCut() {
		if(state.getSelectionStart() != state.getSelectionEnd())
			state.getClipboard().setContents(state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()));
		state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + state.getBufferContent().substring(state.getSelectionEnd()));
		state.setSelectionEnd(state.getSelectionStart());
		count = ++counter;
		careTaker.add(saveStateToMemento(), count);
	}

	@Override
	public void editorPaste() {
		if(!state.getClipboard().isEmpty()) {
			state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + state.getClipboard().getContents() + state.getBufferContent().substring(state.getSelectionEnd()));
			int value = state.getSelectionStart() + state.getClipboard().getContents().length();
			state.setSelectionEnd(value);
			state.setSelectionStart(value);
			count = ++counter;
			careTaker.add(saveStateToMemento(), count);
		}
	}
	
	@Override
	public void editorDelete() {
		state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + state.getBufferContent().substring(state.getSelectionEnd()));
		state.setSelectionEnd(state.getSelectionStart());
		count = ++counter;
		careTaker.add(saveStateToMemento(), count);
	}
	
	@Override
	public void undo() {
		if(counter > 0) {
			counter--;
			getStateFromMemento(careTaker.get(counter));
		}
	}
	
	@Override
	public void redo() {
		if(counter < count) {
			counter++;
			getStateFromMemento(careTaker.get(counter));
		}
	}
}
