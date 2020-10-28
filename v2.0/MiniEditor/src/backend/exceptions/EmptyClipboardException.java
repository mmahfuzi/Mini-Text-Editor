package backend.exceptions;

public class EmptyClipboardException extends Exception {
	
	public EmptyClipboardException() {
		
	}

	public EmptyClipboardException(String message) {
		super(message);	
	}
	
	public EmptyClipboardException(Throwable cause) {
		super(cause);
	}

	public EmptyClipboardException(String message, Throwable cause) {
		super(message, cause);
	}
}
