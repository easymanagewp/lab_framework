package cn.vant.base.system.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;
import cn.vant.base.system.service.IAccountService;
import cn.vant.base.system.service.IDutyService;
import cn.vant.base.system.service.IUserService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.DutyVo;
import cn.vant.base.system.vo.UserVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:25:40 </strong> <br>
 * <strong>概要 : 用户信息  action</strong> <br>
 */
@Controller("sys.userAction")
@RequestMapping("sys/user")
@Logger(busInfo="系统管理",content="用户管理")
public class UserAction extends BaseAction<UserVo>{
	
	final String VIEW_PATH = "/sys/user/user";
	@Autowired IAccountService accountService;
	@Autowired IDutyService dutyService;
	
	@Autowired IUserService userService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="更新/授权")
	public ModelAndView update(UserVo vo, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		ModelAndView mav = super.update(vo, attr, request, response);
		mav.addObject("id", vo.getId());
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	
	@Override
	public ModelAndView edit(UserVo vo, HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		List<AccountVo> accountList = null;
		List<DutyVo> dutyList = null;
		try {
			accountList = accountService.list(vo.getId());
			dutyList = dutyService.list();
		} catch (InvalidIdException e) {
			log.info("无效的用户Id", e);
		} 
		ModelAndView mav = super.edit(vo, request, response);
		mav.addObject("accountList", accountList);
		mav.addObject("dutyList", dutyList);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET,params={"simple=true"})
	public View simpleList(UserVo uv,
			@RequestParam(value="roleId",required=false)String roleId,
			@RequestParam(value="orgId",required=false)String orgId,
			@RequestParam(value="dutyId",required=false)String dutyId
			){
		List<Map<String,Object>> result = null;
		Exception exception = null;
		try {
			result = userService.getSimpleUsers(uv,roleId,orgId,dutyId);
		} catch (GlobalException e) {
			exception = e;
		}
		return new RestView(result, exception);
	}
	
}
