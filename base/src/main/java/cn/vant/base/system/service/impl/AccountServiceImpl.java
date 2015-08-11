package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.BeanUtils;
import cn.core.framework.utils.MapUtils;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IAccountDao;
import cn.vant.base.system.dao.IAccountRoleDao;
import cn.vant.base.system.dao.IOrgDao;
import cn.vant.base.system.dao.IRoleDao;
import cn.vant.base.system.dao.IUserDao;
import cn.vant.base.system.exception.PasswordNotMatchException;
import cn.vant.base.system.exception.UserNameNotFindException;
import cn.vant.base.system.po.Account;
import cn.vant.base.system.po.AccountRole;
import cn.vant.base.system.po.Org;
import cn.vant.base.system.po.User;
import cn.vant.base.system.service.IAccountService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.OrgVo;
import cn.vant.base.system.vo.UserVo;

@Service("sys.accountService")
public class AccountServiceImpl extends BaseServiceImpl<AccountVo, Account> implements
		IAccountService {

	@Autowired IUserDao userDao;
	@Autowired IRoleDao roleDao;
	@Autowired IOrgDao orgDao;
	@Autowired IAccountDao accountDao;
	@Autowired IAccountRoleDao accountRoleDao;
	
	@Override
	public List<AccountVo> list(String userId) throws InvalidIdException, GlobalException {
		if(StrUtils.isEmpty(userId)) throw new InvalidIdException();
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("user.id",QueryCondition.EQ,userId));
		return super.list(queryList);
	}
	
	
	
	
	@Override
	public void add(AccountVo v) throws GlobalException {
		Account po = vo2Po(v);
		
		User user = userDao.findById(v.getUserVo().getId());
		if(null==user) throw new GlobalException("为获取到有效用户对象");
		po.setUser(user);
		
		Org org = orgDao.findById(v.getOrgVo().getId());
		if(null==org) throw new GlobalException("为获取到有效组织对象");
		po.setOrg(org);
		
		// 密码加密
		po.setPassword(StrUtils.md5(po.getPassword()));
		
		accountDao.add(po);
		saveAccountRole(po, v.getRoleIds());
	}
	
	@Override
	public void update(AccountVo v) throws InvalidIdException,
			EntityNotFindException, GlobalException {
		Account po = accountDao.findById(v.getId());
		String oldPwd = po.getPassword();
		BeanUtils.copyProperties(v, po, new String[]{"id"});
		
		Org org = orgDao.findById(v.getOrgVo().getId());
		if(null==org) throw new EntityNotFindException("为获取到有效组织对象");
		po.setOrg(org);
		
		saveAccountRole(po, v.getRoleIds());
		
		String newPwd = v.getPassword();
		if(newPwd!=oldPwd){		// 密码不相等，证明有修改，更新密码
			newPwd = StrUtils.md5(newPwd);
		}
		po.setPassword(newPwd);
		accountDao.update(po);
	}
	
	@Override
	public AccountVo po2Vo(Account p) throws GlobalException {
		AccountVo vo = super.po2Vo(p);
		UserVo userVo = new UserVo().toVo(p.getUser());
		vo.setUserVo(userVo);
		
		OrgVo orgVo = new OrgVo().toVo(p.getOrg());
		vo.setOrgVo(orgVo);
		
		return vo;
	}
	
	void saveAccountRole(Account account,String roleIds) throws GlobalException{
		try {
			accountRoleDao.deleteByAccountId(account.getId());
		} catch (InvalidIdException e) {
			throw new GlobalException("按账户删除 账户角色信息出错",e);
		}
		
		AccountRole accountRole = null;
		for(String id:StrUtils.split(roleIds, ",")){
			accountRole = new AccountRole();
			accountRole.setAccount(account);
			accountRole.setRole(roleDao.findById(id));
			accountRoleDao.add(accountRole);
		}
	}

	@Override
	public List<Map<String, Object>> getSimpleListByUserId(String userId)
			throws GlobalException {
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("user.id",QueryCondition.EQ,userId));
		
		List<Account> accounts = accountDao.query(queryList, null, 0, 0);
		return MapUtils.objects2MapList(accounts, "id","loginName");
	}
	
	@Override
	public AccountVo login(String loginName, String password) throws UserNameNotFindException,PasswordNotMatchException, GlobalException {
		List<QueryCondition> qcs = new ArrayList<QueryCondition>();
		qcs.add(new QueryCondition("loginName",QueryCondition.EQ,loginName));
		List<Account> accounts = accountDao.query(qcs, null,-1,-1);
		if(CollectionUtils.isEmpty(accounts)){
			throw new UserNameNotFindException();
		} 
		Account account = accounts.get(0);
		password = StrUtils.md5(password);
		if(!StringUtils.equalsIgnoreCase(password, account.getPassword())){
			throw new PasswordNotMatchException();
		}
		return po2Vo(account);
	}
	
	
}
