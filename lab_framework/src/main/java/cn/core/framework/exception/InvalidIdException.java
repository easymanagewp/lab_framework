package cn.core.framework.exception;


public class InvalidIdException extends Exception {

	private static final long serialVersionUID = 6567646528465246556L;

	public InvalidIdException() {
		super();
	}

	public InvalidIdException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
	}

	public InvalidIdException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidIdException(String message) {
		super(message);
	}

	public InvalidIdException(Throwable cause) {
		super(cause);
	}

}
