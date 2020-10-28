package backend.command;

import backend.receiver.MiniEditor;

public class UndoCommand extends EditorCommand {

	public UndoCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.undo();
	}
}
