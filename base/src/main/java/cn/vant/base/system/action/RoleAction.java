package cn.vant.base.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;
import cn.core.framework.utils.MapUtils;
import cn.vant.base.system.service.IFunctionService;
import cn.vant.base.system.service.IRoleFunService;
import cn.vant.base.system.service.IRoleService;
import cn.vant.base.system.vo.RoleFunVo;
import cn.vant.base.system.vo.RoleVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:37 </strong> <br>
 * <strong>概要 : 角色action</strong> <br>
 */
@Controller("sys.roleAction")
@RequestMapping("sys/role")
@Logger(busInfo="系统管理",content="角色管理")
public class RoleAction extends BaseAction<RoleVo>{
	
	final String VIEW_PATH = "/sys/role/role";
	
	@Autowired IRoleFunService roleFunService;
	@Autowired IRoleService roleService;
	@Autowired IFunctionService functionService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	
	@Override
	@Logger(type=LoggerType.Update,function="角色更新/授权")
	public ModelAndView update(RoleVo vo, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		ModelAndView mav = super.update(vo,attr, request, response);
		mav.addObject("id", vo.getId());
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	
	@RequestMapping(value="funTree",produces="application/json;charset=utf-8")
	public @ResponseBody String funTree(String roleId)throws GlobalException, InvalidIdException {
		List<RoleFunVo> roleFunList = roleFunService.list(roleId);
		String checkedFunctionIds = "";
		for(RoleFunVo vo:roleFunList){
			checkedFunctionIds += vo.getFunctionVo().getId();
			checkedFunctionIds += ",";
		}
		
		StringBuffer treeBuf = roleFunService.getFunctionCheckTree(roleId,checkedFunctionIds, "402881fb4d524293014d5278c41a0000");
		return treeBuf.toString();
	}
	
	@RequestMapping(method=RequestMethod.GET,params={"simple=true","tree=true"})
	public View simpleList(){
		Object result=null;
		Exception exception = null;
		try {
			List<RoleVo> roleFunList = roleService.list();
			result = MapUtils.objects2MapList(roleFunList, "id","name");
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(result,exception);
	}
	
}
