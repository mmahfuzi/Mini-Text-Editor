package backend.command;

import backend.MiniEditor;

/**
 * DeleteCommand class for command design pattern.
 * @author mukrram
 *
 */
public class DeleteCommand extends EditorCommand{

	public DeleteCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorDelete();
	}
}
