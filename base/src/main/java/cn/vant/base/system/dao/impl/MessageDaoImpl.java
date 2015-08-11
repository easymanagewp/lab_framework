package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IMessageDao;
import cn.vant.base.system.po.Message;

@Repository("sys.messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements IMessageDao {}
