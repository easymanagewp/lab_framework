package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.po.Po.Status;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.BeanUtils;
import cn.core.framework.utils.MapUtils;
import cn.core.framework.utils.PropertyFilter;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IAccountDao;
import cn.vant.base.system.dao.IAccountRoleDao;
import cn.vant.base.system.dao.IDutyDao;
import cn.vant.base.system.dao.IUserDao;
import cn.vant.base.system.po.Account;
import cn.vant.base.system.po.AccountRole;
import cn.vant.base.system.po.Duty;
import cn.vant.base.system.po.User;
import cn.vant.base.system.service.IUserService;
import cn.vant.base.system.vo.UserVo;

@Service("sys.userService")
public class UserServiceImpl extends BaseServiceImpl<UserVo, User> implements
		IUserService {
	@Autowired private IUserDao userDao;
	@Autowired private IAccountDao accountDao;
	@Autowired private IAccountRoleDao accountRoleDao;
	@Autowired private IDutyDao dutyDao;
	
	@Override
	public List<Map<String, Object>> getSimpleUsers(UserVo userVo,
			String roleId, String orgId, String dutyId) throws GlobalException {
		List<QueryCondition> qcs = new ArrayList<QueryCondition>();
		List<Account> resultAccounts = new ArrayList<Account>();
		
		qcs.add(new QueryCondition("isDel", QueryCondition.EQ, Status.N));
		
		if(StringUtils.isNotBlank(roleId)){
			qcs.add(new QueryCondition("role.id",QueryCondition.EQ,roleId));
			List<AccountRole> ars = accountRoleDao.query(qcs, null, 0, 0);
			for(AccountRole ar : ars){
				resultAccounts.add(ar.getAccount());
			}
		}else if(StringUtils.isNotBlank(orgId)){
			qcs.add(new QueryCondition("org.id",QueryCondition.EQ,orgId));
			resultAccounts = accountDao.query(qcs, null, 0, 0);
		}else if(StringUtils.isNotBlank(dutyId)){
			qcs.add(new QueryCondition("user.duties",QueryCondition.EQ,dutyId));
			resultAccounts = accountDao.query(qcs, null, 0, 0);
		}else{
			resultAccounts = accountDao.query(qcs,null,0,0);
		}
		Set<User> users = new HashSet<User>();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Account account : resultAccounts){
			users.add(account.getUser());
			result.add(MapUtils.object2Map(account, new PropertyFilter(){

				@Override
				public void filter(Map<String, Object> result, String property,
						Object value) {
					if(property.equals("user.id")){
						result.put("parentId", value);
					}else if(property.equals("loginName")){
						result.put("name", value);
					}else{
						result.put(property, value);
					}
				}
				
			}, "user.id","id","loginName"));
		}
		for(User user : users){
			result.add(MapUtils.object2Map(user, "id","name"));
		}
		
		return result;
		
	}
	
	@Override
	public void update(UserVo v) throws InvalidIdException,
			EntityNotFindException, GlobalException {
		if(StrUtils.isBlankOrNull(v.getId())) throw new InvalidIdException();
		User p = userDao.findById(v.getId());
		if(null==p) throw new EntityNotFindException();
		BeanUtils.copyProperties(v, p, new String[]{"id"});
		if(null != v.getDutiesVo() && StringUtils.isNotBlank(v.getDutiesVo().getId())){
			Duty duty = dutyDao.findById(v.getDutiesVo().getId());
			p.setDuties(duty);
		}
		userDao.update(p);
	}
}


