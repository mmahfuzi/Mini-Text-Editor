package backend.client;

import backend.MiniEditorTextInterfaceWithCommand;
import backend.command.Command;
import backend.command.CopyCommand;
import backend.command.CutCommand;
import backend.command.DeleteCommand;
import backend.command.InsertCommand;
import backend.command.PasteCommand;
import backend.command.ReplayRecordCommand;
import backend.command.SelectCommand;
import backend.command.StartRecordCommand;
import backend.command.StopRecordCommand;
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
	public void startRecord() {
		cmd = new StartRecordCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void stopRecord() {
		cmd = new StopRecordCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void replay() {
		cmd = new ReplayRecordCommand(editor);
		invoker = new MiniEditorInvoker(cmd);
		invoker.action();
	}
	public void register(MiniEditorTextInterfaceWithCommand fe) {
		((MiniEditorImpl)editor).getState().register(fe);
	}
}
