package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IFunctionDao;
import cn.vant.base.system.po.Function;

@Repository("sys.functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {}
