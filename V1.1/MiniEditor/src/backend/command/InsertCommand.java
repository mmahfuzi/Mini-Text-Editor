package backend.command;

import backend.receiver.MiniEditor;

/**
 * Concrete command of Command design pattern
 * @author mukrram
 *
 */
public class InsertCommand extends EditorCommand{
	
	private String textToInsert;                      // Text to insert to miniEditor
	
	public InsertCommand(String textToInsert, MiniEditor editor) {
		super(editor);
		this.textToInsert = textToInsert;
	}
	
	@Override
	public void execute() {                            // This concrete command gives its own definition of execute method
		editor.editorInsert(textToInsert);
	}

}
