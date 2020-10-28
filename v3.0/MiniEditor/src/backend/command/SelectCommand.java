package backend.command;

import backend.MiniEditor;
/**
 * SelectCommand class for command design pattern.
 * @author mukrram
 *
 */
public class SelectCommand extends EditorCommand{
	
	private int selectionStart;
	private int selectionEnd;
	
	public SelectCommand(int selectionStart, int selectionEnd, MiniEditor editor) {
		super(editor);
		this.selectionStart = selectionStart;
		this.selectionEnd = selectionEnd;
	}
	
	@Override
	public void execute() { 
		try {
			editor.editorSelect(selectionStart, selectionEnd);	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
