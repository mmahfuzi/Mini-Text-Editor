package backend.receiver;

import backend.exceptions.EmptyClipboardException;
import backend.exceptions.InvalidOperationExceptionException;
import backend.exceptions.InvalidSelectionException;

public class MiniEditorImpl implements MiniEditor {

	private State state;
	private CareTaker careTaker;
	private int counter, count, recordingStart, recordingStop;
	private boolean recordingStatus = false;
	
	public MiniEditorImpl() {
		state = new State();
		careTaker = new CareTaker();
		careTaker.add(saveStateToMemento(),count);
	}
	
	@Override
	public String getBuffer() {
		return state.getBufferContent();
	}

	@Override
	public String getSelection() {
		return state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()) + "[" + state.getSelectionStart() + "," + state.getSelectionEnd() + "]";
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
    	setState(new State(Memento.getState()));
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
		try {
			if(state.getSelectionStart() != state.getSelectionEnd()) {
				state.getClipboard().setContents(state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()));
				count = ++counter;
				careTaker.add(saveStateToMemento(), count);
			} else
				throw new InvalidSelectionException();
		} catch(InvalidSelectionException ise) {
			System.out.println("11111111112222");
		}
	}

	@Override
	public void editorCut() {
		try {
			if(state.getSelectionStart() != state.getSelectionEnd()) {
				state.getClipboard().setContents(state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()));
				state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + state.getBufferContent().substring(state.getSelectionEnd()));
				state.setSelectionEnd(state.getSelectionStart());
				count = ++counter;
				careTaker.add(saveStateToMemento(), count);
			} else 
				throw new InvalidSelectionException();
		} catch(InvalidSelectionException ise) {
			System.out.println("Nothing selected.");
		}
	}

	@Override
	public void editorPaste() {
		try {
			if(!state.getClipboard().isEmpty()) {
				state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + state.getClipboard().getContents() + state.getBufferContent().substring(state.getSelectionEnd()));
				int value = state.getSelectionStart() + state.getClipboard().getContents().length();
				state.setSelectionStart(value);
				state.setSelectionEnd(value);
				count = ++counter;
				careTaker.add(saveStateToMemento(), count);
			} else 
				throw new EmptyClipboardException("");
		} catch(EmptyClipboardException ece) {
			System.out.println("Nothing to paste.");
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
	public void startRecord() {
		try {
			if(recordingStatus == false) {
				recordingStart = counter + 1;
				recordingStatus = true;
				System.out.println("Recording started.");
			} else
				throw new InvalidOperationExceptionException("Recording already started.");
		} catch(InvalidOperationExceptionException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	@Override
	public void stopRecord() {
		try {
			if(recordingStatus == true) {
				recordingStop = counter + 1;
				recordingStatus = false;
				System.out.println("Recording stoped.");
			} else
				throw new InvalidOperationExceptionException("No recording session found.");
		} catch(InvalidOperationExceptionException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	@Override
	public void replay() {
		try {	
			if(recordingStart < recordingStop) {
				int counterVar = recordingStart;
				while(counterVar < recordingStop) {
					getStateFromMemento(careTaker.get(counterVar));
					counterVar++;
				}
				System.out.println("Current state.");
				getStateFromMemento(careTaker.get(counter));
			} else if (recordingStart == recordingStop)
				throw new InvalidOperationExceptionException("Nothing to play.");
			else
				throw new InvalidOperationExceptionException("Please stop recording first.");
		} catch(InvalidOperationExceptionException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	
	
}
