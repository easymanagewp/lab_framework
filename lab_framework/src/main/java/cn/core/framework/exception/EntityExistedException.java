package cn.core.framework.exception;

public class EntityExistedException extends Exception {
	private static final long serialVersionUID = -446414699994592237L;

	public EntityExistedException() {
		super();
	}

	public EntityExistedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
	}

	public EntityExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityExistedException(String message) {
		super(message);
	}

	
	public EntityExistedException(Throwable cause) {
		super(cause);
	}
	
	
}
