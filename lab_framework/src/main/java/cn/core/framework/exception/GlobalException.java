package cn.core.framework.exception;
@SuppressWarnings("serial")
public class GlobalException extends Exception {
	
	public GlobalException() {
		super();
	}

	public GlobalException(String msg) {
		super(msg);
	}
	
	public GlobalException(Exception e) {
		super(e);
	}

	public GlobalException(String msg, Exception e) {
		super(msg, e);
	}
}
