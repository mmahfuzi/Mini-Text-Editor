package backend.command;

import backend.MiniEditor;

/**
 * InsertCommand class for command design pattern.
 * @author mukrram
 *
 */
public class InsertCommand extends EditorCommand{
	
	private String textToInsert;
	
	public InsertCommand(String textToInsert, MiniEditor editor) {
		super(editor);
		this.textToInsert = textToInsert;
	}
	
	@Override
	public void execute() {
		editor.editorInsert(textToInsert);
	}

}
