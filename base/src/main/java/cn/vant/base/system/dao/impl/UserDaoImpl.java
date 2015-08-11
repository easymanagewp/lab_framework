package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IUserDao;
import cn.vant.base.system.po.User;

@Repository("sys.userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {}
