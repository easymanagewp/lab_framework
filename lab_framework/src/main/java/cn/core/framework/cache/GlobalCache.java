package cn.core.framework.cache;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import cn.core.framework.config.Constant;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * <strong>创建信息:</strong>Roy Wang 2015年7月9日 上午10:27:06  <br>
 * <strong>概要 : </strong> <br>
 * 全局的数据缓存对象，缓存域包含两个部分：<br>
 * 1：内存和硬盘缓存<br>
 * 2：session缓存（如果用户尚未登录的情况下，使用session缓存<br>
 */
public class GlobalCache extends GeneralCacheAdministrator{
	private static final long serialVersionUID = 2541523998874518782L;
	
	private static GlobalCache cache = new GlobalCache();
	private GlobalCache() {}
	
	public void put(String ticket,String key,Object value){
		cache.putInCache(ticket+"_"+key, value);
	}
	
	public Object get(String ticket,String key){
		try {
			return cache.getFromCache(ticket+"_"+key);
		} catch (NeedsRefreshException e) {
			return null;
		}
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月9日 上午10:52:30 <br>
	 * <strong>概要 : </strong> <br>
	 * 添加对象到缓存池之内<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param request 用户的Request请求对象，以此判断用户是否登录，如果登录的话，将缓存对象到缓存池内，负责缓存到session之内
	 * @param key 缓存对象标识，之后以此来获取对象
	 * @param value 需要进行缓存的对象
	 */
	public void put(HttpServletRequest request,String key,Object value){
		Cookie cookie = WebUtils.getCookie(request, Constant.User.TOCKET_COOKIE_KEY);
		if(null == cookie || StringUtils.isBlank(cookie.getValue())){
			WebUtils.setSessionAttribute(request, key, value);
		}else{
			cache.putInCache(cookie.getValue()+"_"+key, value);
		}
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月9日 上午10:54:09 <br>
	 * <strong>概要 : </strong> <br>
	 * 移除指定的缓存对象<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param request 用户的Request请求对象，以此判断用户是否登录，如果是已登录用户，将同时从用户的缓存域之内移除对象
	 * @param key 需要移除的对象key
	 */
	public void remove(HttpServletRequest request,String key){
		Cookie cookie = WebUtils.getCookie(request, Constant.User.TOCKET_COOKIE_KEY);
		request.getSession().removeAttribute(key);
		if(null != cookie && StringUtils.isNotBlank(cookie.getValue())){
			cache.flushEntry(cookie.getValue()+"_"+key);;
		}
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月9日 上午10:56:38 <br>
	 * <strong>概要 : </strong> <br>
	 * 获取缓存对象<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param request 用户的Request请求对象，以此来判断用户是否登录，如果是已登录用户，那么将优先从对象缓存域内获取用户对象
	 * @param key
	 * @return
	 */
	public Object get(HttpServletRequest request,String key){
		Cookie cookie  = WebUtils.getCookie(request, Constant.User.TOCKET_COOKIE_KEY);
		if(null != cookie && StringUtils.isNotBlank(cookie.getValue())){
			try {
				Object value = cache.getFromCache(cookie.getValue()+"_"+key);
				if(null != value){
					return value;
				}
			} catch (NeedsRefreshException e) {}
		}
		
		Object o = WebUtils.getSessionAttribute(request, key);
		return o;
	}
	
	public static GlobalCache getInstance(){
		return cache;
	}
}
