package backend.command;

import backend.MiniEditor;
/**
 * PasteCommand class for command design pattern.
 * @author mukrram
 *
 */
public class PasteCommand extends EditorCommand{

	public PasteCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorPaste();
	}
}
