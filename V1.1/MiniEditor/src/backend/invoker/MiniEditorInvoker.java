package backend.invoker;

/**
 * Invoker for Command design pattern
 */
import backend.command.Command;

public class MiniEditorInvoker {

	private Command command;                   // Command  to hold concrete command
	
	public MiniEditorInvoker(Command command) {
		this.command = command;
	}
	
	public void action() {                      // It calls execute method of whatever command it has.
		command.execute();
	}
}
