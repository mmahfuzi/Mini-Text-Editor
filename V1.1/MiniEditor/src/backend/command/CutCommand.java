package backend.command;

import backend.receiver.MiniEditor;

/**
 * Concrete command of Command design pattern
 * @author mukrram
 *
 */
public class CutCommand extends EditorCommand{

	public CutCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {                      // This concrete command gives its own definition of execute method
		editor.editorCut();
	}
}
