package exceptions;

public class EmptyListException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyListException() {
		super("The list needs at least one element!");
	}
	
}
