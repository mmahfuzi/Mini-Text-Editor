package backend.command;

import backend.MiniEditor;
/**
 * UndoCommand class for command design pattern.
 * @author mukrram
 *
 */
public class UndoCommand extends EditorCommand {

	public UndoCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorUndo();
	}
}
