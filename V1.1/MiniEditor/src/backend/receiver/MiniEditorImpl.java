package backend.receiver;

import backend.exceptions.InvalidSelectionException;

/**
 * Concrete Receiver of command design pattern
 * @author mukrram
 *
 */
public class MiniEditorImpl implements MiniEditor{

	private int selectionStart;                  // To hold start of current selection
	private int selectionEnd;                    // To hold end of current selection
	private String bufferContent;                // To hold buffer content
	private Clipboard clipboard;                 // To hold current clipboard content
	
	public MiniEditorImpl() {
		bufferContent = "";
		clipboard = new Clipboard();
	}
	
	@Override
	public String getBuffer() {                  //  Method to return buffer content
		return bufferContent;
	}

	@Override
	public String getSelection() {              // Method to return current selection
		return bufferContent.substring(selectionStart, selectionEnd) + "[" + selectionStart + "," + selectionEnd + "]";
	}

	@Override
	public String getClipboard() {              // Method to return content of clipboard
		return clipboard.getContents();
	}

	@Override
	public void editorInsert(String substring) {      // Method to insert text to Minieditor
		bufferContent = bufferContent.substring(0, selectionStart) + substring + bufferContent.substring(selectionEnd);
		selectionStart = selectionEnd = selectionStart + substring.length();
	}

	@Override
	public void editorSelect(int start, int stop) {   // Method to select text from Minieditor
		try {
			if((start >= 0 && start <= bufferContent.length()) && (stop >=0 && stop  <= bufferContent.length()) && (stop >= start)) {
				selectionStart = start;
				selectionEnd = stop;
			} else {
				throw new InvalidSelectionException();
			}
		} catch(InvalidSelectionException ise) {
			System.out.println("ERROR! Invalid selection.");
		}
	}

	@Override
	public void editorCopy() {                         // Method to copy text from Minieditor
		if(selectionStart != selectionEnd)
			clipboard.setContents(bufferContent.substring(selectionStart, selectionEnd));
	}

	@Override
	public void editorCut() {                          // Method to cut text from Minieditor
		editorCopy();
		editorDelete();
	}

	@Override
	public void editorPaste() {                        // Method to paste text to Minieditor
		if(!clipboard.isEmpty()) {
			bufferContent = bufferContent.substring(0, selectionStart) + clipboard.getContents() + bufferContent.substring(selectionEnd);
			selectionStart = selectionEnd = selectionStart + clipboard.getContents().length();
		}
	}
	
	@Override
	public void editorDelete() {                       // Method to delete text from Minieditor
		bufferContent = bufferContent.substring(0, selectionStart) + bufferContent.substring(selectionEnd);
		selectionEnd = selectionStart;
	}
}
