package backend;

import frontend.MiniEditorTextInterfaceWithCommand;
import backend.command.Command;
import backend.command.CopyCommand;
import backend.command.CutCommand;
import backend.command.DeleteCommand;
import backend.command.InsertCommand;
import backend.command.OpenCommand;
import backend.command.PasteCommand;
import backend.command.RedoCommand;
import backend.command.ReplayRecordCommand;
import backend.command.SelectCommand;
import backend.command.StartRecordCommand;
import backend.command.StopRecordCommand;
import backend.command.UndoCommand;
import backend.command.WriteCommand;
import backend.invoker.MiniEditorInvoker;

/**
 * Class used as client for command design pattern
 * @author mukrram
 *
 */
public class MiniEditorClient {
	private MiniEditor editor;
	private Command cmd;
	private MiniEditorInvoker invoker;
	
	public MiniEditorClient() {
		editor = new MiniEditorImpl();
	}
	public void insert(String text) {                    // Method to call operation for insert text
		cmd = new InsertCommand(text, editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void select(int start, int end) {             // Method to call operation for select text
		cmd = new SelectCommand(start, end, editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void copy() {                                // Method to call operation for copy text
		cmd = new CopyCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void cut() {                                 // Method to call operation for cut text
		cmd = new CutCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void paste() {                               // Method to call operation for paste text
		cmd = new PasteCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void delete() {                             // Method to call operation for delete text
		cmd = new DeleteCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void startRecord() {                      // Method to call operation for start recording
		cmd = new StartRecordCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void stopRecord() {                       // Method to call operation for stop recording
		cmd = new StopRecordCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void replay() {                           // Method to call operation for replaying
		cmd = new ReplayRecordCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void register(MiniEditorTextInterfaceWithCommand fe) {    // Method to register 
		((MiniEditorImpl)editor).getState().register(fe);
	}
	public void undo() {                             // Method to call operation for undo
		cmd = new UndoCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void redo() {                            // Method to call operation for redo
		cmd = new RedoCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void open(String filePath) {             // Method to call operation for read from file.
		cmd = new OpenCommand(editor, filePath);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void write(String filePath) {            // Method to call operation for write to file.
		cmd = new WriteCommand(editor, filePath);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
}
