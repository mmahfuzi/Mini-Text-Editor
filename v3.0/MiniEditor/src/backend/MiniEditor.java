package backend;

/**
 * Class used as receiver for command design pattern
 * @author mukrram
 *
 */
public interface MiniEditor
{
	public String getBuffer();                          // Method to get the buffer content.
	public String getSelection();                       // Method to get current selection
	public String getClipboard();                       // Method to get Clipboard content
	public void editorInsert(String substring);         // Method to text
	public void editorSelect(int start, int stop);      // Method to select text
	public void editorCopy();                           // Method to copy text
	public void editorCut();                            // Method to cut text
	public void editorPaste() ;                         // Method to paste text
	public void editorDelete();                         // Method to delete text
	public void editorStartRecord();                    // Method to start recording
	public void editorStopRecord();                     // Method to stop recording
	public void editorReplay();                         // Method to replay recording as many times a suser wants
	public void editorUndo();                           // Method to undo
	public void editorRedo();                           // Method to redo
	public void editorOpen(String filePath);            // Method to read from file
	public void editorWrite(String filePath);           // Method to write to file
}
