package backend.command;

import backend.receiver.MiniEditor;

public abstract class EditorCommand implements Command{

	protected MiniEditor editor;
	
	public EditorCommand(MiniEditor editor) {
		this.editor = editor;
	}
}
