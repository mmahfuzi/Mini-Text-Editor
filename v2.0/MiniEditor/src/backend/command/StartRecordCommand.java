package backend.command;

import backend.receiver.MiniEditor;

public class StartRecordCommand extends EditorCommand {

	public StartRecordCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.startRecord();
	}
}
