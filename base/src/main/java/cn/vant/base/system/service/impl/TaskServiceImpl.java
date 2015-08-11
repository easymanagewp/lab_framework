package cn.vant.base.system.service.impl;

import org.springframework.stereotype.Service;
import cn.vant.base.system.po.Task;
import cn.vant.base.system.vo.TaskVo;
import cn.vant.base.system.service.ITaskService;
import cn.core.framework.common.service.BaseServiceImpl;

@Service("sys.taskService")
public class TaskServiceImpl extends BaseServiceImpl<TaskVo,Task> implements
		ITaskService {
}
