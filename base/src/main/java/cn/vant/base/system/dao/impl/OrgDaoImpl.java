package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IOrgDao;
import cn.vant.base.system.po.Org;

@Repository("sys.orgDao")
public class OrgDaoImpl extends BaseDaoImpl<Org> implements IOrgDao {}
