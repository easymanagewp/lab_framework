package cn.core.framework.exception;


/**
 * <strong>Author : 王鹏 </strong> <br>
 * <strong>Create on : 2015年1月23日 上午9:38:24 </strong> <br>
 * <strong>Log : </strong> <br>
 * <div>
 * 当业务逻辑不允许实体为null的时候，检查发现实体为null，抛出此异常，代表异常业务逻辑
 * </div>
 */
public class EntityNotFindException extends Exception {
	private static final long serialVersionUID = 4989098870139919298L;

	public EntityNotFindException() {
		super();
	}

	public EntityNotFindException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
	}

	public EntityNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFindException(String message) {
		super(message);
	}

	public EntityNotFindException(Throwable cause) {
		super(cause);
	}

	
}
