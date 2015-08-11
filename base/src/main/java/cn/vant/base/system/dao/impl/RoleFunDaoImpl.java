package cn.vant.base.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IRoleFunDao;
import cn.vant.base.system.po.RoleFun;

@Repository("sys.roleFunDao")
public class RoleFunDaoImpl extends BaseDaoImpl<RoleFun> implements IRoleFunDao {

	@Override
	public void deleteByRoleId(String roleId) throws InvalidIdException {
		super.deleteAll(list(roleId));
	}

	@Override
	public List<RoleFun> list(String roleId) throws InvalidIdException {
		if(StrUtils.isEmpty(roleId)) throw new InvalidIdException();
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("role.id",QueryCondition.EQ,roleId));
		return (List<RoleFun>)super.query(queryList, null, -1, -1);
	}
}
