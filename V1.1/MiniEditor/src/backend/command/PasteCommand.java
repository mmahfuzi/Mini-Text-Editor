package backend.command;

import backend.receiver.MiniEditor;

/**
 * Concrete command of Command design pattern
 * @author mukrram
 *
 */
public class PasteCommand extends EditorCommand{

	public PasteCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {                       // This concrete command gives its own definition of execute method
		editor.editorPaste();
	}
}
