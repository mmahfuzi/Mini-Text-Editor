package backend.client;

import backend.command.Command;
import backend.command.CopyCommand;
import backend.command.CutCommand;
import backend.command.DeleteCommand;
import backend.command.InsertCommand;
import backend.command.PasteCommand;
import backend.command.RedoCommand;
import backend.command.SelectCommand;
import backend.command.UndoCommand;
import backend.invoker.MiniEditorInvoker;
import backend.receiver.MiniEditor;
import backend.receiver.MiniEditorImpl;

public class MiniEditorClient {
	private MiniEditor editor;
	private Command cmd;
	private MiniEditorInvoker invoker;
	
	public MiniEditorClient() {
		editor = new MiniEditorImpl();
	}
	public void insert(String text) {
		cmd = new InsertCommand(text, editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void select(int start, int end) {
		cmd = new SelectCommand(start, end, editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void copy() {
		cmd = new CopyCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void cut() {
		cmd = new CutCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void paste() {
		cmd = new PasteCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void delete() {
		cmd = new DeleteCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void undo() {
		cmd = new UndoCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void redo() {
		cmd = new RedoCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public String getBufferText() {
		return editor.getBuffer();
	}
	public String getSelectionText() {
		return editor.getSelection();
	}
}
