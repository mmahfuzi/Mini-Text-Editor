package backend.client;

import backend.command.Command;
import backend.command.CopyCommand;
import backend.command.CutCommand;
import backend.command.DeleteCommand;
import backend.command.InsertCommand;
import backend.command.PasteCommand;
import backend.command.SelectCommand;
import backend.invoker.MiniEditorInvoker;
import backend.receiver.MiniEditor;
import backend.receiver.MiniEditorImpl;

/**
 * Client of Command design pattern.
 * @author mukrram
 *
 */
public class MiniEditorClient {
	private MiniEditor editor;                       // Used to get buffer and selection.
	private Command cmd;                             // To hold a command object depending on user command.
	private MiniEditorInvoker invoker;               // Invoker of command design pattern.
	
	public MiniEditorClient() {
		editor = new MiniEditorImpl();
	}
	public void insert(String text) {
		cmd = new InsertCommand(text, editor);       // Will be called if user command is I/i
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void select(int start, int end) {
		cmd = new SelectCommand(start, end, editor); // Will be called if user command is S/s
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void copy() {
		cmd = new CopyCommand(editor);               // Will be called if user command is C/c
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void cut() {
		cmd = new CutCommand(editor);                // Will be called if user command is X/x
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void paste() {
		cmd = new PasteCommand(editor);             // Will be called if user command is P/p
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void delete() {                          // Will be called if user command is D/d
		cmd = new DeleteCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public String getBufferText() {
		return editor.getBuffer();                  // Gets buffer from the receiver.
	}
	public String getSelectionText() {
		return editor.getSelection();               // Gets selection from the receiver.
	}
}
