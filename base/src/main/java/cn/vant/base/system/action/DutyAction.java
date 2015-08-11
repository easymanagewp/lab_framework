package cn.vant.base.system.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.spring.RestView;
import cn.core.framework.utils.MapUtils;
import cn.vant.base.system.service.IDutyService;
import cn.vant.base.system.vo.DutyVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:27:00 </strong> <br>
 * <strong>概要 :岗位action </strong> <br>
 */
@Controller("sys.dutyAction")
@RequestMapping("sys/duty")
@Logger(busInfo="系统管理",content="岗位管理")
public class DutyAction extends BaseAction<DutyVo>{
	
	final String VIEW_PATH = "/sys/duty/duty";
	
	//#--- 自动注入Service
	@Autowired private IDutyService dutyService;
	//---#
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	//#--- Rest接口，获取岗位列表
	@RequestMapping(method=RequestMethod.GET,params={"simple=true"})
	public View simpleList(){
		Object result = null;
		Exception exception = null;
		try {
			List<DutyVo> dutys = dutyService.list();
			result = MapUtils.objects2MapList(dutys, "id","name");
		} catch (Exception e){
			exception = e;
		}
		return new RestView(result,exception);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="{id}")
	public View findNameById(@PathVariable("id")String id){
		Object result = null;
		Exception exception = null;
		try {
			DutyVo duty = dutyService.findById(id);
			result = duty.getName();
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(result,exception);
	}
	//---#
	
	
}
