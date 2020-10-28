package backend.command;

import backend.receiver.MiniEditor;

public class CopyCommand extends EditorCommand{
	
	public CopyCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorCopy();
	}
	

}
