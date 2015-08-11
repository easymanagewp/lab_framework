package cn.vant.base.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IFavoriteDao;
import cn.vant.base.system.po.Favorite;

@Repository("sys.favoriteDao")
public class FavoriteDaoImpl extends BaseDaoImpl<Favorite> implements IFavoriteDao {

	@Override
	public List<Favorite> listByRoleFunId(String roleFunId) {
		if(StrUtils.isEmpty(roleFunId)) return null;
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("roleFun.id",QueryCondition.EQ,roleFunId));
		
		return (List<Favorite>)super.query(queryList, null, -1, -1);
	}
	
	@Override
	public List<Favorite> listByAccountId(String accountId) {
		if(StrUtils.isEmpty(accountId)) return null;
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("account.id",QueryCondition.EQ,accountId));
		
		return (List<Favorite>)super.query(queryList, null, 0, 1);
	}

	@Override
	public void deleteByRoleFunId(String roleFunId) {
		super.deleteAll(listByRoleFunId(roleFunId));
	}

	@Override
	public void deleteByAccountId(String accountId) {
		super.deleteAll(listByAccountId(accountId));
	}
}
