package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.ILogDao;
import cn.vant.base.system.po.Log;

@Repository("sys.logDao")
public class LogDaoImpl extends BaseDaoImpl<Log> implements ILogDao {}
	