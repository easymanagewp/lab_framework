package cn.core.framework.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ActionHandleInterceptor extends HandlerInterceptorAdapter{

	private static final Logger log = LoggerFactory.getLogger(ActionHandleInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info("--------preHandle-----------");
		return super.preHandle(request, response, handler);
	}
}
