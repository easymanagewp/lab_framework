package cn.vant.base.system.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import cn.core.framework.cache.GlobalCache;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.exception.NoAuthorityException;
import cn.vant.base.system.service.IFunctionService;
import cn.vant.base.system.vo.FunctionVo;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired private IFunctionService functionService;
	
	private UrlPathHelper pathHelper = new UrlPathHelper();
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		List<String> cachedUrls = (List<String>) GlobalCache.getInstance().get(Constant.Cache.COMMON_CACHE_KEY, Constant.Function.ALL_FUNCTION_CACHE_KEY);
		
		if(CollectionUtils.isEmpty(cachedUrls)){
			List<FunctionVo> functionVos = functionService.list();
			cachedUrls = new ArrayList<String>();
			for(FunctionVo functionVo : functionVos){
				if(StringUtils.isNotEmpty(functionVo.getUrl())){
					cachedUrls.add(functionVo.getUrl());
				}
			}
			GlobalCache.getInstance().put(Constant.Cache.COMMON_CACHE_KEY, Constant.Function.ALL_FUNCTION_CACHE_KEY, cachedUrls);
		}
		
		
		
		String requestUrl = pathHelper.getLookupPathForRequest(request);
		if(cachedUrls.contains(requestUrl)){
			List<String> userCachedUrls = (List<String>)GlobalCache.getInstance().get(request, Constant.Function.USER_FUNCTION_CACHE_KEY);
			if(CollectionUtils.isEmpty(userCachedUrls)){
				List<FunctionVo> userFunctionVos = (List<FunctionVo>) GlobalCache.getInstance().get(request, Constant.User.FUNCTION_INFO_CACHE_KEY);
				userCachedUrls = new ArrayList<String>();
				for(FunctionVo functionVo : userFunctionVos){
					if(StringUtils.isNotEmpty(functionVo.getUrl())){
						userCachedUrls.add(functionVo.getUrl());
					}
				}
				GlobalCache.getInstance().put(request, Constant.Function.USER_FUNCTION_CACHE_KEY,userCachedUrls);
			}
			
			if(userCachedUrls.contains(requestUrl)){
				return super.preHandle(request, response, handler);
			}else{
				throw new NoAuthorityException();
			}
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
}
