package backend.command;

import backend.receiver.MiniEditor;

public class PasteCommand extends EditorCommand{

	public PasteCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorPaste();
	}
}
