package cn.core.framework.dao.impl;

import org.springframework.stereotype.Repository;
import cn.core.framework.dao.IUserDao;
import cn.core.framework.po.User;
import cn.core.framework.common.dao.BaseDaoImpl;

@Repository("sys.userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
}
