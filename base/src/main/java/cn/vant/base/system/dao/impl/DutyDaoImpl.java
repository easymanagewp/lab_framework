package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IDutyDao;
import cn.vant.base.system.po.Duty;

@Repository("sys.dutyDao")
public class DutyDaoImpl extends BaseDaoImpl<Duty> implements IDutyDao {}
