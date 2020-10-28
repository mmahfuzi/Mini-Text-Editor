package backend.command;

import backend.MiniEditor;
/**
 * ReplayCommand class for command design pattern.
 * @author mukrram
 *
 */
public class ReplayRecordCommand extends EditorCommand {

	public ReplayRecordCommand(MiniEditor editor) {
		super(editor);
	}
	
	@Override
	public void execute() {
		editor.editorReplay();
	}
}
