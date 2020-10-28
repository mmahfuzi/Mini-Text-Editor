package backend.command;

import backend.receiver.MiniEditor;

public class ReplayRecordCommand extends EditorCommand {

	public ReplayRecordCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.replay();
	}
}
