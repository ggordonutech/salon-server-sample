package salon.server;

public class InvalidServerOptionException extends Exception {
	
	public InvalidServerOptionException() {
		super("The option provided does not exist.");
	}
	public InvalidServerOptionException(String message) {
		super(message);
	}

}
