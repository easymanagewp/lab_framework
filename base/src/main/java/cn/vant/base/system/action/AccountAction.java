package cn.vant.base.system.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidFileTypeException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;
import cn.core.framework.utils.MapUtils;
import cn.core.framework.utils.PropertyFilter;
import cn.vant.base.system.service.IAccountRoleService;
import cn.vant.base.system.service.IAccountService;
import cn.vant.base.system.service.IOrgService;
import cn.vant.base.system.service.IRoleService;
import cn.vant.base.system.service.IUploadService;
import cn.vant.base.system.service.IUserService;
import cn.vant.base.system.vo.AccountRoleVo;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.OrgVo;
import cn.vant.base.system.vo.RoleVo;
import cn.vant.base.system.vo.UserVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:26:32 </strong> <br>
 * <strong>概要 :账户信息action </strong> <br>
 */
@Controller("sys.accountAction")
@RequestMapping("sys/account")
@Logger(busInfo="系统管理",content="账户管理")
public class AccountAction extends BaseAction<AccountVo>{
	
	final String VIEW_PATH = "/sys/account/account";
	
	@Autowired IAccountService accountService;
	@Autowired IOrgService orgService;
	@Autowired IRoleService roleService;
	@Autowired IAccountRoleService accountRoleService;
	@Autowired IUserService userService;
	@Autowired IUploadService uploadService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="更新账户信息")
	public ModelAndView update(AccountVo vo, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		saveUpload(vo);
		ModelAndView mav = super.update(vo, attr, request, response);
		mav.addObject("id", vo.getId());
		mav.addObject("userVo.id", vo.getUserVo().getId());
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="添加账户信息")
	public ModelAndView add(AccountVo vo, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		saveUpload(vo);
		ModelAndView mav = super.add(vo, attr, request, response);
		mav.addObject("id", vo.getId());
		mav.addObject("userVo.id", vo.getUserVo().getId());
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	
	private void saveUpload(AccountVo vo) throws GlobalException {
		String path = null;
		try {
			path = uploadService.add(vo.getFile(), AccountVo.SYS_SIGNATURE,null);
		} catch (InvalidFileTypeException e) {
			throw new GlobalException("非法文件类型",e);
		} 
		vo.setSignature(path);
	}
	
	@Override
	public ModelAndView edit(AccountVo vo, HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		List<AccountVo> accountList = null;
		ModelAndView mav = new ModelAndView();
		UserVo userVo = null;
		AccountVo accountVo = null;
		boolean isNewAccount = false;
		
		//用户信息
		try {
			accountList = accountService.list(vo.getUserVo().getId());
			mav.addObject("accountList", accountList);
			userVo = userService.findById(vo.getUserVo().getId());
			mav.addObject(IS_EDIT, true);
		} catch (InvalidIdException e) {
			log.info("无效的用户Id", e);
		} catch (EntityNotFindException e) {
			log.info("未获取到有效用户信息", e);
		}
		
		//当前账户信息
		try {
			accountVo = accountService.findById(vo.getId());
		} catch (InvalidIdException e) {
			log.info("无效的ID ",e);
			accountVo = new AccountVo();
			accountVo.setUserVo(userVo);
			isNewAccount = true;
		} catch (EntityNotFindException e) {
			log.info("未获取到有效用户信息",e);
		}
	
		List<AccountRoleVo> accountRoleList = null ;
		try {
			accountRoleList = accountRoleService.list(vo.getId());
		} catch (InvalidIdException e) {
			log.info("无效的账户ID ",e);
		}
		
		mav.addObject("accountRoleList", accountRoleList);
		
		List<RoleVo> roleList =	roleService.list();
		List<OrgVo> orgList =	orgService.treeList();
		mav.addObject("roleList", roleList);
		mav.addObject("orgList", orgList);
		
		mav.addObject("isNewAccount", isNewAccount);
		mav.addObject("vo", accountVo);
		mav.setViewName(getViewPath()+"_edit");
		return mav;
	}
	
	@RequestMapping(value="select",method=RequestMethod.GET)
	public String select(){
		return getViewPath()+"_select";
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月13日 上午10:24:39 <br>
	 * <strong>概要 : </strong> <br>
	 * 根据用户id获取简单的账户列表
	 * <strong>修改记录 : </strong> <br>
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,params={"simple=true","userId!=null"})
	public View simpleList(@RequestParam("userId")String userId){
		List<Map<String,Object>> result = null;
		Exception exception = null;
		try {
			result = accountService.getSimpleListByUserId(userId);
		} catch (GlobalException e) {
			exception = e;
		}
		return new RestView(result, exception);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET,params={"simple=true"})
	public View simpleAccount(@PathVariable("id")String id){
		Map<String,Object> result = null;
		Exception exception = null;
		try {
			AccountVo accountVo = accountService.findById(id);
			result = MapUtils.object2Map(accountVo, new PropertyFilter(){

				@Override
				public void filter(Map<String, Object> result, String property,
						Object value) {
					if("userVo.id".equals(property)){
						result.put("userId", value);
					}else if("userVo.name".equals(property)){
						result.put("userName", value);
					}else{
						result.put(property, value);
					}
				}
				
			},"id","loginName","userVo.id","userVo.name");
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(result,exception);
	}
}
