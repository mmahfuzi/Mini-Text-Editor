package backend.exceptions;

public class InvalidOperationExceptionException extends Exception {
	
	public InvalidOperationExceptionException() {
		
	}

	public InvalidOperationExceptionException(String message) {
		super(message);	
	}
	
	public InvalidOperationExceptionException(Throwable cause) {
		super(cause);
	}

	public InvalidOperationExceptionException(String message, Throwable cause) {
		super(message, cause);
	}
}
