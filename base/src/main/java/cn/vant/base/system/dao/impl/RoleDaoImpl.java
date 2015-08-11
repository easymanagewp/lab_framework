package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IRoleDao;
import cn.vant.base.system.po.Role;

@Repository("sys.roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {}
