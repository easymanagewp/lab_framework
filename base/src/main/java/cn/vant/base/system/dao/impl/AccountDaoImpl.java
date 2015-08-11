package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.vant.base.system.dao.IAccountDao;
import cn.vant.base.system.po.Account;

@Repository("sys.accountDao")
public class AccountDaoImpl extends BaseDaoImpl<Account> implements IAccountDao {}
