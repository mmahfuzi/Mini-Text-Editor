package backend.command;

import backend.receiver.MiniEditor;

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
