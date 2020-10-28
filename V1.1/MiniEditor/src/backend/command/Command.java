package backend.command;

/**
 * Command for Command design pattern.
 * 
 *
 */
public interface Command {
	
	public void execute();   // Each concrete command will implement this method.
}
