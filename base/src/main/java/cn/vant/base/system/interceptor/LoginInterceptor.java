package cn.vant.base.system.interceptor;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import cn.core.framework.cache.GlobalCache;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.exception.NoLoginException;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private List<String> notLoginUri;
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	public List<String> getNotLoginUri() {
		return notLoginUri;
	}

	public void setNotLoginUri(List<String> notLoginUri) {
		this.notLoginUri = notLoginUri;
	}

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = urlPathHelper.getLookupPathForRequest(request);
		if(notLoginUri.contains(url)){
			return super.preHandle(request, response, handler);
		}
		Cookie cookie = WebUtils.getCookie(request, Constant.User.TOCKET_COOKIE_KEY);
		if(null == cookie){
			log.info("无登录凭证，跳转登录页面");
			throw new NoLoginException();
		}else if(null == GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY)){
			log.info("无账户信息，跳转登录页面");
			throw new NoLoginException();
		}
		
		return super.preHandle(request, response, handler);
	}
}
