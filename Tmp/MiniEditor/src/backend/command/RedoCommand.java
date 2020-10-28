package backend.command;

import backend.receiver.MiniEditor;

public class RedoCommand extends EditorCommand {

	public RedoCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.redo();
	}
}
