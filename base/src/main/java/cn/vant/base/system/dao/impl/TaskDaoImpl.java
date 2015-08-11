package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;
import cn.vant.base.system.dao.ITaskDao;
import cn.vant.base.system.po.Task;
import cn.core.framework.common.dao.BaseDaoImpl;

@Repository("sys.taskDao")
public class TaskDaoImpl extends BaseDaoImpl<Task> implements ITaskDao {
}
