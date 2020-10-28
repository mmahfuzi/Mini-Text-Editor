package backend.command;

import backend.MiniEditor;
/**
 * RedoCommand class for command design pattern.
 * @author mukrram
 *
 */
public class RedoCommand extends EditorCommand {

	public RedoCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorRedo();
	}
}
