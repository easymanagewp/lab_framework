package cn.vant.base.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patchca.utils.CpatchcaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.common.action.Action;
import cn.core.framework.common.action.Status;
import cn.core.framework.config.Constant;
import cn.core.framework.utils.CookieUtils;
import cn.core.framework.utils.UUIDUtils;
import cn.vant.base.system.service.IAccountRoleService;
import cn.vant.base.system.service.IAccountService;
import cn.vant.base.system.vo.AccountRoleVo;
import cn.vant.base.system.vo.AccountVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:27:44 </strong> <br>
 * <strong>概要 :登录action </strong> <br>
 */
@Controller("sys.loginAction")
public class LoginAction extends Action {
	public static final String VIEW_PATH = "/sys/home/";
	
	@Autowired private IAccountService accountService;
	@Autowired private IAccountRoleService accountRoleService;
	
	@RequestMapping("login.do")
	public ModelAndView login(String username,String password,String captcha,RedirectAttributes attr,HttpServletRequest request,
			HttpServletResponse response) {
		log.info("登录系统");
		Status status = new Status();
		status.setInfo("登录");
		ModelAndView mav = new ModelAndView();
		//验证码
		if (!CpatchcaUtils.validate(request, "captcha")) {
			mav=index();
			status.setStatus("登录失败");
			status.setMessage("验证码输入有误");
			mav.addObject("status", status);
			return mav;
		}
		
		
		try{
			AccountVo account = accountService.login(username, password);
			String ticket = UUIDUtils.getUUID();
			CookieUtils.addCookie(response, Constant.User.TOCKET_COOKIE_KEY, ticket, Integer.MAX_VALUE);
			// 使用ticket作为标示，防止过期的cookie内存在同key ticket；
			GlobalCache.getInstance().put(ticket, cn.vant.base.system.config.Constant.User.USER_INFO_CACHE_KEY, account);
			List<AccountRoleVo> arvs = accountRoleService.list(account.getId());
			GlobalCache.getInstance().put(ticket, cn.vant.base.system.config.Constant.User.USER_ROLE_INFO, arvs);
			log.info("登录成功，跳转至HOME页");
			mav.setViewName("redirect:sys/home/main.do");
			return mav;
		}catch(Exception e){
			e.printStackTrace();
			mav=index();
			status.setStatus("登录失败");
			status.setMessage("用户名或密码错误");
			mav.addObject("status", status);
			return mav;
		}
		
		
//		if(!"admin".equals(username)){
//			mav=index();
//			status.setStatus("登录失败");
//			status.setMessage("XXX信息有误");
//			mav.addObject("status", status);
//			return mav;
//		}
		
//		log.info("登录成功，跳转至HOME页");
//		mav.setViewName("redirect:sys/home/main.do");;
//		return mav;
	}
	
	@RequestMapping("index.do")
	public ModelAndView index() {
		log.info("跳转到登录页面");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PATH+"login");
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpServletResponse response) {
		CookieUtils.addCookie(response, Constant.User.TOCKET_COOKIE_KEY, null, 0);
		log.info("登出系统");
		return index();
	}

	public String getViewPath() {
		return VIEW_PATH;
	}
	
}