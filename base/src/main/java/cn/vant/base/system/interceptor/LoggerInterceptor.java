package cn.vant.base.system.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.exception.EntityExistedException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.interceptor.AbstractLoggerInterceptor;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.utils.DateUtils;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.dao.ILogDao;
import cn.vant.base.system.po.Log;
import cn.vant.base.system.service.ILogService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.LogVo;

public class LoggerInterceptor extends AbstractLoggerInterceptor{

	@Autowired private ILogService logService;
	
	@Override
	protected void doLogger(HttpServletRequest request,
			HttpServletResponse response, Object handler,String busInfo, String content, String function,
			LoggerType loggerType, String uri, String ip) throws Exception {
		LogVo log = new LogVo();
		log.setBusInfo(busInfo);
		log.setContent(content);
		log.setFunction(function);
		log.setType(loggerType.toString());
		log.setIp(ip);
		log.setSubject(uri);
		
		Cookie cookie = WebUtils.getCookie(request, Constant.User.TOCKET_COOKIE_KEY);
		if(null != cookie && StringUtils.isNotBlank(cookie.getValue())){
			AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
			if(null != accountVo){
				log.setUser(accountVo.getUserVo().getName() + "(" + accountVo.getLoginName() + ")");
			}
		}
		
		log.setTime(DateUtils.getCurrDateTimeStr());
		logService.add(log);
	}

}
