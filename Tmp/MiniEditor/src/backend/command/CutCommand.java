package backend.command;

import backend.receiver.MiniEditor;

public class CutCommand extends EditorCommand{

	public CutCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorCut();
	}
}
