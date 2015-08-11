package cn.core.framework.exception;


public class InvalidFileTypeException extends Exception {

	private static final long serialVersionUID = 6567646528465246556L;

	public InvalidFileTypeException() {
		super();
	}

	public InvalidFileTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
	}

	public InvalidFileTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidFileTypeException(String message) {
		super(message);
	}

	public InvalidFileTypeException(Throwable cause) {
		super(cause);
	}

}
