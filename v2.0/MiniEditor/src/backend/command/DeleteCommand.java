package backend.command;

import backend.receiver.MiniEditor;

public class DeleteCommand extends EditorCommand{

	public DeleteCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorDelete();
	}
}
