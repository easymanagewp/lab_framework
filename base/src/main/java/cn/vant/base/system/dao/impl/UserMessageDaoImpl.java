package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;
import cn.vant.base.system.dao.IUserMessageDao;
import cn.vant.base.system.po.UserMessage;
import cn.core.framework.common.dao.BaseDaoImpl;

@Repository("sys.user.messageDao")
public class UserMessageDaoImpl extends BaseDaoImpl<UserMessage> implements IUserMessageDao {
}
