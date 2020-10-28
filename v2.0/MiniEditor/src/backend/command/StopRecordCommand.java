package backend.command;

import backend.receiver.MiniEditor;

public class StopRecordCommand extends EditorCommand {

	public StopRecordCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.stopRecord();
	}
}
