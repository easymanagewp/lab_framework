package cn.core.framework.interceptor;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import cn.core.framework.utils.DateUtils;

public class MappingExceptionResolver extends SimpleMappingExceptionResolver  {

	private Logger log = Logger.getLogger(MappingExceptionResolver.class);
	
	 @Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) {
		ModelAndView mav = super.doResolveException(request, response, arg2, e);
		String code = DateUtils.format(new Date(), "yyyyMMddHHmmssS");
		log.error("系统异常，错误编码:"+code, e);
		mav.addObject("error_code", code);
		return mav;
	}

}
