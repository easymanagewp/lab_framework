package cn.core.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;

/**
 * 业务日志拦截器
 * @author 王鹏
 *
 */
public abstract class AbstractLoggerInterceptor extends HandlerInterceptorAdapter {

	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Object bean = handlerMethod.getBean();
		Logger logger = bean.getClass().getAnnotation(Logger.class);
		if(null != logger){
			String busInfo = logger.busInfo();
			String content = logger.content();
			logger = handlerMethod.getMethodAnnotation(Logger.class);
			if(null != logger){
				String function = logger.function();
				LoggerType loggerType = logger.type();
				String uri = urlPathHelper.getLookupPathForRequest(request);
				String ip = request.getRemoteAddr();
				this.doLogger(request,response,handler,busInfo,content,function,loggerType,uri,ip);
			}
		}
		return super.preHandle(request, response, handler);
	}

	protected abstract void doLogger(HttpServletRequest request,HttpServletResponse response, Object handler,String busInfo, String content, String function,
			LoggerType loggerType, String uri, String ip) throws Exception;
}
