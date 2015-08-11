package cn.vant.base.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.log.annotation.Logger;
import cn.vant.base.system.service.ITemplateService;
import cn.vant.base.system.vo.TemplateVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:37 </strong> <br>
 * <strong>概要 : 导入导出模板action</strong> <br>
 */
@Controller("sys.templateAction")
@RequestMapping("/sys/template")
@Logger(busInfo="系统管理",content="模版管理")
public class TemplateAction extends BaseAction<TemplateVo> {
	final String VIEW_PATH = "/sys/template/template";
	
	@Autowired ITemplateService templateService;
	
	@Override
	public String getViewPath() {
		return VIEW_PATH;
	}
}