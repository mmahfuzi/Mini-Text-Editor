package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import backend.exceptions.EmptyClipboardException;
import backend.exceptions.InvalidOperationExceptionException;
import backend.exceptions.InvalidPathException;
import backend.exceptions.InvalidSelectionException;

public class MiniEditorImpl implements MiniEditor {

	private State state;                                        // State of the text editor
	private CareTaker careTaker;
	private int counter, count, recordingStart, recordingStop;
	private boolean recordingStatus = false;
	
	public MiniEditorImpl() {
		state = new State();
		careTaker = new CareTaker();
		careTaker.add(saveStateToMemento(),count);
	}
	
	@Override
	public String getBuffer() {                                  // Method to get buffer content
		return state.getBufferContent();
	}

	@Override
	public String getSelection() {                              // Method to get selection
		return state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()) + "[" + state.getSelectionStart() + "," + state.getSelectionEnd() + "]";
	}
	
	public void setState(State state){                           // Method to set state
       this.state = state;
    }

    public State getState(){                                     // Method to get state.
       return state;
    }

    public Memento saveStateToMemento(){                        // Method to save state to memento
	    return new Memento(new State(state));
    }

    public void getStateFromMemento(Memento Memento){           // Method to get state from memento
    	setState(new State(Memento.getState()));
    } 
	
	@Override
	public String getClipboard() {                              // Method to get clipboard
		return state.getClipboard().getContents();
	}

	@Override
	public void editorInsert(String substring) {               // Method to insert text
		state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + substring + state.getBufferContent().substring(state.getSelectionEnd()));
		int value = state.getSelectionStart() + substring.length();
		state.setSelectionStart(value);
		state.setSelectionEnd(value);
		count = ++counter;
		careTaker.add(saveStateToMemento(), count);
	}

	@Override
	public void editorSelect(int start, int stop) {            // Method to select text
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
	public void editorCopy() {                                 // Method to copy text
		try {
			if(state.getSelectionStart() != state.getSelectionEnd()) {
				state.getClipboard().setContents(state.getBufferContent().substring(state.getSelectionStart(), state.getSelectionEnd()));
				count = ++counter;
				careTaker.add(saveStateToMemento(), count);
			} else
				throw new InvalidSelectionException();
		} catch(InvalidSelectionException ise) {
		}
	}

	@Override
	public void editorCut() {                                  // Method to cut text
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
	public void editorPaste() {                                // Method to paste text
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
	public void editorDelete() {                                // Method to delete text
		state.setBufferContent(state.getBufferContent().substring(0, state.getSelectionStart()) + state.getBufferContent().substring(state.getSelectionEnd()));
		state.setSelectionEnd(state.getSelectionStart());
		count = ++counter;
		careTaker.add(saveStateToMemento(), count);
	}

	@Override
	public void editorStartRecord() {                            // Method to start recording
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
	public void editorStopRecord() {                            // Method to stop recording
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
	public void editorReplay() {                                // Method to replay
		try {
			if(recordingStart <= count && (recordingStop-1) <= count) {
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
			} else 
				throw new InvalidOperationExceptionException("Recording removed.");
		} catch(InvalidOperationExceptionException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	@Override
	public void editorUndo() {                                        // Method to undo
		if(counter > 0) {
			counter--;
			getStateFromMemento(careTaker.get(counter));
		}
	}
	
	@Override
	public void editorRedo() {                                        // Method to redo
		if(counter < count) {
			counter++;
			getStateFromMemento(careTaker.get(counter));
		}
	}

	@Override
	public void editorOpen(String filePath) {                         // Method to read from file
		try {
			if(new File(filePath).exists()) {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String line, data = "";
				while ((line = br.readLine()) != null) {
					data += line;
				}
				state.setBufferContent(data);
				int value = data.length();
				state.setSelectionStart(value);
				state.setSelectionEnd(value);
				count = ++counter;
				careTaker.add(saveStateToMemento(), count);
				br.close();
			} else
				throw new InvalidPathException("Specified path is not valid.");
		} catch(InvalidPathException ipe) {
			System.out.println(ipe.getMessage());
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void editorWrite(String filePath) {                             // Method to write to file
		try {
			if(new File(filePath).exists()) {
				PrintWriter pw = new PrintWriter(new FileWriter(filePath));
				pw.println(state.getBufferContent());
				pw.close();
			} else
				throw new InvalidPathException("Specified path is not valid.");
		} catch(InvalidPathException ipe) {
			System.out.println(ipe.getMessage());
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
