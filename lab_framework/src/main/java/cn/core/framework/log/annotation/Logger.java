package cn.core.framework.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志记录器
 * @author 王鹏
 */	
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD,ElementType.TYPE})
public @interface Logger {
	
	/**
	 * 业务系统
	 * @return
	 */
	public String busInfo() default "";
	
	/**
	 * 功能
	 * @return
	 */
	public String function() default "";
	
	/**
	 * 模块
	 * @return
	 */
	public String content() default "";
	
	/**
	 * 类型
	 * @return
	 */
	public LoggerType type() default LoggerType.Null;
	
	
}
