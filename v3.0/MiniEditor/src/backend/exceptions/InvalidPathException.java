package backend.exceptions;

/**
 * Custom exception class
 * @author mukrram
 *
 */
public class InvalidPathException extends Exception {
	
	public InvalidPathException() {
		
	}

	public InvalidPathException(String message) {
		super(message);	
	}
	
	public InvalidPathException(Throwable cause) {
		super(cause);
	}

	public InvalidPathException(String message, Throwable cause) {
		super(message, cause);
	}
}
