package cn.vant.base.system.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.log.annotation.Logger;
import cn.vant.base.system.vo.TaskVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:37 </strong> <br>
 * <strong>概要 : 托盘任务action</strong> <br>
 */
@Controller("sys.taskAction")
@RequestMapping("/sys/task")
@Logger(busInfo="系统管理",content="任务管理")
public class TaskAction extends BaseAction<TaskVo> {
	final String VIEW_PATH = "/sys/task/task";
		
	@Override
	public String getViewPath() {
		return VIEW_PATH;
	}
}