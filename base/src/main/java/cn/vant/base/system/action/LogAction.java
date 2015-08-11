package cn.vant.base.system.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.core.framework.common.action.BaseAction;
import cn.vant.base.system.vo.LogVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:27:32 </strong> <br>
 * <strong>概要 : 业务日志action</strong> <br>
 */
@Controller("sys.logAction")
@RequestMapping("sys/log")
public class LogAction extends BaseAction<LogVo>{
	
	final String VIEW_PATH = "/sys/log/log";
	
	public String getViewPath() {
		return VIEW_PATH;
	}
}
