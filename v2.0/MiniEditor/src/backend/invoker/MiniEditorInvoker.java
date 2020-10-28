package backend.invoker;

import backend.command.Command;

public class MiniEditorInvoker {

	Command command;
	
	public MiniEditorInvoker(Command command) {
		this.command = command;
	}
	
	public void action() {
		command.execute();
	}
}
