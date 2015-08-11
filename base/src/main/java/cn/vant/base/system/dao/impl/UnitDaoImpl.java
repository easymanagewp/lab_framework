package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IUnitDao;
import cn.vant.base.system.po.Unit;

@Repository("sys.unitDao")
public class UnitDaoImpl extends BaseDaoImpl<Unit> implements IUnitDao {}
