package backend.exceptions;

public class InvalidSelectionException extends Exception {
	
	public InvalidSelectionException() {
		
	}

	public InvalidSelectionException(String message) {
		super(message);	
	}
	
	public InvalidSelectionException(Throwable cause) {
		super(cause);
	}

	public InvalidSelectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
