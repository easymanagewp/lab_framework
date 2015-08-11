package cn.core.framework.spring;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import cn.core.framework.listener.SpringListener;

public class ServiceBeforeMethodAdvice implements MethodBeforeAdvice{
	private static Logger log = LoggerFactory.getLogger(SpringListener.class);
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		log.info(arg0.getName()+"before method~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
