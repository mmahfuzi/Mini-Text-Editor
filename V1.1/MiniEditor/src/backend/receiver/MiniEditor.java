package backend.receiver;

/**
 * Receiver of command design pattern
 * @author mukrram
 *
 */
public interface MiniEditor
{
	public String getBuffer();                       // Method to return buffer content
	public String getSelection();                    // Method to return current selection
	public String getClipboard();                    // Method to return content of clipboard
	public void editorInsert(String substring);      // Method to insert text to Minieditor
	public void editorSelect(int start, int stop);   // Method to select text from Minieditor
	public void editorCopy();                        // Method to copy text from Minieditor
	public void editorCut();                         // Method to cut text from Minieditor
	public void editorPaste() ;                      // Method to paste text to Minieditor
	public void editorDelete();                      // Method to delete text from Minieditor
}
