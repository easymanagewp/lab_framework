package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.dao.IAccountRoleDao;
import cn.vant.base.system.po.AccountRole;
import cn.vant.base.system.service.IAccountRoleService;
import cn.vant.base.system.vo.AccountRoleVo;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.RoleVo;

@Service("sys.accountRoleService")
public class AccountRoleServiceImpl extends BaseServiceImpl<AccountRoleVo, AccountRole> implements
		IAccountRoleService {
	
	@Autowired IAccountRoleDao accountRoleDao;
	
	@Override
	public List<AccountRoleVo> list(String accountId)
			throws InvalidIdException, GlobalException {
		List<AccountRole> pList = accountRoleDao.listByAccountId(accountId);
		List<AccountRoleVo> vList = new ArrayList<AccountRoleVo>();
		for(AccountRole p:pList){
			vList.add(po2Vo(p));
		}
		return vList;
	}
	
	@Override
	public AccountRoleVo po2Vo(AccountRole p) throws GlobalException {
		AccountRoleVo vo = super.po2Vo(p);
		vo.setAccountVo(new AccountVo().toVo(p.getAccount()));
		vo.setRoleVo(new RoleVo().toVo(p.getRole()));
		return vo;
	}
	
}
