package cn.vant.base.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IAccountRoleDao;
import cn.vant.base.system.po.AccountRole;

@Repository("sys.accountRoleDao")
public class AccountRoleDaoImpl extends BaseDaoImpl<AccountRole> implements IAccountRoleDao {

	@Override
	public void deleteByAccountId(String accountId) throws InvalidIdException {
		super.deleteAll(listByAccountId(accountId));
	}

	@Override
	public List<AccountRole> listByAccountId(String accountId)
			throws InvalidIdException {
		if(StrUtils.isEmpty(accountId)) throw new InvalidIdException();
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("account.id",QueryCondition.EQ,accountId));
		return (List<AccountRole>)super.query(queryList, null, -1, -1);
	}


	@Override
	public List<AccountRole> listByRoleId(String roleId)
			throws InvalidIdException, GlobalException {
		if(StrUtils.isEmpty(roleId)) throw new InvalidIdException();
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("role.id",QueryCondition.EQ,roleId));
		return (List<AccountRole>)super.query(queryList, null, -1, -1);
	}

}
