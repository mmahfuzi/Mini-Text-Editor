package backend.command;

import backend.MiniEditor;
/**
 * OpenCommand class for command design pattern.
 * @author mukrram
 *
 */
public class OpenCommand extends EditorCommand {

	private String filePath;
	
	public OpenCommand(MiniEditor editor, String filePath) {
		super(editor);
		this.filePath = filePath;
	}
	
	@Override
	public void execute() {
		editor.editorOpen(filePath);
	}
}
