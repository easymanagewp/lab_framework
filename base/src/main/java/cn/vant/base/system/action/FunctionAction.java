package cn.vant.base.system.action;

import java.util.List;
import java.util.Map;

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

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;
import cn.core.framework.utils.MapUtils;
import cn.core.framework.utils.PropertyFilter;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.service.IFunctionService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.FunctionVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:27:09 </strong> <br>
 * <strong>概要 : 功能action</strong> <br>
 */
@Controller("sys.functionAction")
@RequestMapping("sys/function")
@Logger(busInfo="系统管理",content="功能菜单管理")
public class FunctionAction extends BaseAction<FunctionVo>{
	
	final String VIEW_PATH = "/sys/function/function";
	
	@Autowired IFunctionService functionService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@Override
	public ModelAndView edit(FunctionVo vo, HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		ModelAndView mav =  super.edit(vo, request, response);
		//功能列表
		List<FunctionVo> functionList = functionService.treeList();
		mav.addObject("functionList", functionList);
		return mav;
	}
	
	@RequestMapping(value="funTree",produces="application/json;charset=utf-8")
	public @ResponseBody String funTree(String pid)throws GlobalException{
		if(StrUtils.isBlankOrNull(pid))
			pid = functionService.findRoot().getId();
		StringBuffer treeBuf = functionService.tree(pid);
		return treeBuf.toString();
	}
	
	@Override
	@Logger(type=LoggerType.Delete,function="删除菜单")
	public ModelAndView update2del(FunctionVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		ModelAndView mv = super.update2del(v, attr, request, response);
		mv.addObject("pid", v.getPid());
		return mv;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="添加菜单")
	public ModelAndView add(FunctionVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {	
		ModelAndView mv = super.add(v, attr, request, response);
		mv.addObject("pid", v.getParentVo().getId());
		return mv;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="更新菜单")
	public ModelAndView update(FunctionVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {	
		ModelAndView mv = super.update(v, attr, request, response);
		mv.addObject("pid", v.getParentVo().getId());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="all")
	public View allList(HttpServletRequest request){
		Object result = null;
		Exception exception = null;
		try{
			List<FunctionVo> functions = null;
			if(functions==null){
				functions = functionService.list();
			}
			result = MapUtils.objects2MapList(functions, new PropertyFilter(){
				@Override
				public void filter(Map<String, Object> resultMap,
						String property, Object value) {
					if("imageUrl".equals(property)){
						resultMap.put("icon", value);
					}else if("parentVo.id".equals(property)){
						resultMap.put("parentId", value);
					}else{
						resultMap.put(property, value);
					}
				}
				
			}, "id","name","code","url","imageUrl","parentVo.id");
		} catch(Exception e){
			exception = e;
		}
		return new RestView(result, exception);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public View simpleList(HttpServletRequest request){
		Object result = null;
		Exception exception = null;
		try{
			@SuppressWarnings("unchecked")
			List<FunctionVo> functions = (List<FunctionVo>) GlobalCache.getInstance().get(request, cn.vant.base.system.config.Constant.User.FUNCTION_INFO_CACHE_KEY);
			if(functions==null){
				AccountVo account = (AccountVo) GlobalCache.getInstance().get(request, cn.vant.base.system.config.Constant.User.USER_INFO_CACHE_KEY);
				functions = functionService.findUserFunctions(account.getId());
				GlobalCache.getInstance().put(request, cn.vant.base.system.config.Constant.User.FUNCTION_INFO_CACHE_KEY, functions);
			}
			result = MapUtils.objects2MapList(functions, new PropertyFilter(){
				@Override
				public void filter(Map<String, Object> resultMap,
						String property, Object value) {
					if("imageUrl".equals(property)){
						resultMap.put("icon", value);
					}else if("parentVo.id".equals(property)){
						resultMap.put("parentId", value);
					}else{
						resultMap.put(property, value);
					}
				}
				
			}, "id","name","code","url","imageUrl","parentVo.id");
		} catch(Exception e){
			exception = e;
		}
		return new RestView(result, exception);
	}
}
