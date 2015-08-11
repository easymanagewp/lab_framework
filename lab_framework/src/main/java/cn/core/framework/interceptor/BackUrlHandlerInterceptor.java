package cn.core.framework.interceptor;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import cn.core.framework.cache.GlobalCache;

public class BackUrlHandlerInterceptor extends HandlerInterceptorAdapter{
	
	private UrlPathHelper uph = new UrlPathHelper();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = uph.getLookupPathForRequest(request);
		
		// 存在参数，截取，获取单纯的url
		if(requestUrl.contains("?")){
			requestUrl = requestUrl.split("\\?")[0];
		}
		
		if(requestUrl.endsWith("page.do") || requestUrl.endsWith("list.do")){
			
			// 获取参数
			StringBuffer paramsBuf = new StringBuffer();
			Enumeration<String> pNames = request.getParameterNames();
			while(pNames.hasMoreElements()){
				String pName = pNames.nextElement();
				paramsBuf.append(pName).append("=").append(URLEncoder.encode(request.getParameter(pName),"utf-8")).append("&");
			}
			
			if(paramsBuf.length()>0){
				requestUrl += ("?" + paramsBuf.toString());
			}
			
			GlobalCache.getInstance().put(request, "request.page.url", requestUrl);
		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String referer = request.getHeader("Referer");
		referer = referer==null?"":referer;
		
		if(referer.contains("?")){
			referer = referer.split("\\?")[0];
		}
		
		if(referer.endsWith("page.do") || referer.endsWith("list.do")){
			modelAndView.addObject("referer", GlobalCache.getInstance().get(request, "request.page.url"));
		}

		
		// 将参数放入视图
		String requestUrl = uph.getLookupPathForRequest(request);		
		String requestParams = "";
		
		if(requestUrl.contains("?")){
			requestUrl = requestUrl.split("\\?")[0];
			requestParams = request.getQueryString();
		}
		// 存在参数，截取，获取单纯的url
		if(requestUrl.endsWith("page.do") || requestUrl.endsWith("list.do")){
			Enumeration<String> pNames = request.getParameterNames();
			while(pNames.hasMoreElements()){
				String pName = pNames.nextElement();
				modelAndView.addObject(pName, request.getParameter(pName));
			}
			
			if(requestParams != ""){
				for(String requestParam : requestParams.split("&")){		
					String[] requestParamArr = requestParam.split("=");
					modelAndView.addObject(requestParamArr[0], URLDecoder.decode(requestParamArr[1],"utf-8"));
				}
			}
		}
	}
}
