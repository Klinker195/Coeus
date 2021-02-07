package exceptions;

public class InvalidTextException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidTextException() {
		super("The text doesn't meet the correct requirements!");
	}
	
}
