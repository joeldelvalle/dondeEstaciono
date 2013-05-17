package coop.tecso.donde.estaciono.exception;

public class DondeEstacionoServerException extends Exception {

	private static final long serialVersionUID = 3594418898009763070L;

	public DondeEstacionoServerException(String message) {
		super(message);
	}

	public DondeEstacionoServerException(Throwable exception) {
		super(exception);
	}

	public DondeEstacionoServerException(String message, Throwable exception) {
		super(message, exception);
	}

}
