package backend.command;

import backend.receiver.MiniEditor;

/**
 * It comes between command and concrete command.
 * Otherwise each class must have MiniEditor object.
 * @author mukrram
 *
 */
public abstract class EditorCommand implements Command{

	protected MiniEditor editor;
	
	public EditorCommand(MiniEditor editor) {
		this.editor = editor;
	}
}
