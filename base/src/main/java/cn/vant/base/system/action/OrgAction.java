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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;
import cn.core.framework.utils.MapUtils;
import cn.core.framework.utils.PropertyFilter;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.service.IOrgService;
import cn.vant.base.system.vo.OrgVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:27 </strong> <br>
 * <strong>概要 : 组织action</strong> <br>
 */
@Controller("sys.OrgAction")
@RequestMapping("sys/org")
@Logger(busInfo="系统管理",content="组织管理")
public class OrgAction extends BaseAction<OrgVo>{
	
	final String VIEW_PATH = "/sys/org/org";
	
	@Autowired IOrgService orgService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@Override
	public ModelAndView edit(OrgVo vo, HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		ModelAndView mav =  super.edit(vo, request, response);
		//组织列表
		List<OrgVo> orgList = orgService.treeList();
		mav.addObject("orgList", orgList);
		return mav;
	}
	
	@RequestMapping(value="orgTree",produces="application/json;charset=utf-8")
	public @ResponseBody String orgTree(String pid)throws GlobalException{
		if(StrUtils.isBlankOrNull(pid))
			pid = orgService.findRoot().getId();
		StringBuffer treeBuf = orgService.tree(pid);
		return treeBuf.toString();
	}
	
	@Override
	@Logger(type=LoggerType.Delete,function="删除组织信息")
	public ModelAndView update2del(OrgVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		ModelAndView mv = super.update2del(v, attr, request, response);
		mv.addObject("pid", v.getPid());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET,params={"simple=true"})
	public View simpleOrgs(){
		Exception exception = null;
		List<Map<String,Object>> results = null;
		try {
			List<OrgVo> orgVos = orgService.list();
			results = MapUtils.objects2MapList(orgVos,new PropertyFilter(){

				@Override
				public void filter(Map<String, Object> resultMap,
						String property, Object value) {
					if(property.equals("parentVo.id")){
						resultMap.put("parentId", value);
					}else{
						resultMap.put(property, value);
					}
				}
				
			}, "id","name","parentVo.id");
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(results,exception);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET,params={"simple=true"})
	public View findSimpleById(@PathVariable("id")String id){
		Exception exception = null;
		Map<String,Object> results = null;
		try {
			OrgVo orgVo = orgService.findById(id);
			results = MapUtils.object2Map(orgVo, "id","name");
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(results,exception);
	}
	
	@Override
	@Logger(type=LoggerType.Insert,function="添加组织信息")
	public ModelAndView add(OrgVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {	
		ModelAndView mv = super.add(v, attr, request, response);
		mv.addObject("pid", v.getParentVo().getId());
		return mv;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="更新组织信息")
	public ModelAndView update(OrgVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {	
		ModelAndView mv = super.update(v, attr, request, response);
		mv.addObject("pid", v.getParentVo().getId());
		return mv;
	}
}
