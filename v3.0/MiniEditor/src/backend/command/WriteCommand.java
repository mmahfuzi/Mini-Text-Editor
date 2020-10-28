package backend.command;

import backend.MiniEditor;

/**
 * WriteCommand class for command design pattern.
 * @author mukrram
 *
 */
public class WriteCommand extends EditorCommand {

	private String filePath;
	
	public WriteCommand(MiniEditor editor, String filePath) {
		super(editor);
		this.filePath = filePath;
	}
	
	@Override
	public void execute() {
		editor.editorWrite(filePath);
	}
}
