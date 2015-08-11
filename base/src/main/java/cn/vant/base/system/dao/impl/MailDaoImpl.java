package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IMailDao;
import cn.vant.base.system.po.Mail;

@Repository("sys.mailDao")
public class MailDaoImpl extends BaseDaoImpl<Mail> implements IMailDao {}
