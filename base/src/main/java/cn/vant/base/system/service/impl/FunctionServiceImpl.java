package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.page.OrderCondition;
import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.BeanUtils;
import cn.core.framework.utils.CollectionUtils;
import cn.vant.base.system.dao.IAccountRoleDao;
import cn.vant.base.system.dao.IFunctionDao;
import cn.vant.base.system.dao.IRoleDao;
import cn.vant.base.system.dao.IRoleFunDao;
import cn.vant.base.system.po.AccountRole;
import cn.vant.base.system.po.Function;
import cn.vant.base.system.po.RoleFun;
import cn.vant.base.system.service.IFunctionService;
import cn.vant.base.system.vo.FunctionVo;

@Service("sys.functionService")
public class FunctionServiceImpl extends BaseServiceImpl<FunctionVo, Function> implements
		IFunctionService {

	@Autowired IFunctionDao functionDao;
	@Autowired private IRoleDao roleDao;
	@Autowired private IAccountRoleDao accountRoleDao;
	@Autowired private IRoleFunDao roleFunDao;
	
	@Override
	public void add(FunctionVo v) throws GlobalException {
		Function po = vo2Po(v);
		Function function = functionDao.findById(v.getParentVo().getId());
		po.setParent(function);
		functionDao.add(po);
	}
	
	
	
	@Override
	public void update(FunctionVo v) throws InvalidIdException,
			EntityNotFindException, GlobalException {
		Function Function = functionDao.findById(v.getId());
		BeanUtils.copyProperties(v, Function, new String[]{"id"});
	
		Function parent = functionDao.findById(v.getParentVo().getId());
		Function.setParent(parent);
		
		functionDao.update(Function);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public PageResult pageResult(FunctionVo v, PageResult pageResult)
			throws GlobalException {
		List<QueryCondition> queryList = pageResult.getQueryList();
		if(CollectionUtils.isEmpty(queryList))
			queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("parent.id",QueryCondition.EQ,v.getPid()));
		pageResult.setQueryList(queryList);
		
		return super.pageResult(v, pageResult);
	}
	
	@Override
	public FunctionVo po2Vo(Function p) throws GlobalException {
		FunctionVo vo = super.po2Vo(p);
		if(null!=p.getParent())
			vo.setPid(p.getParent().getId());
		return vo;
	}
	
	public List<FunctionVo> findUserFunctions(String accountId) throws GlobalException{
		List<QueryCondition> qcs = new ArrayList<QueryCondition>();
		qcs.add(new QueryCondition("account.id",QueryCondition.EQ,accountId));
		List<AccountRole> ars = accountRoleDao.query(qcs, null, -1, -1);
		
		qcs = new ArrayList<QueryCondition>();
		StringBuffer likeRoles = new StringBuffer("(");
		for(AccountRole ar : ars){
			likeRoles.append("'"+ar.getRole().getId()+"',");
		}
		likeRoles = new StringBuffer(likeRoles.substring(0, likeRoles.length()-1));
		likeRoles.append(")");
		qcs.add(new QueryCondition("role.id in "+likeRoles.toString()));
		
		List<OrderCondition> ocs = new ArrayList<OrderCondition>();
		ocs.add(new OrderCondition(OrderCondition.ORDER_ASC,"function.sort"));
		
		List<RoleFun> roleFuns = roleFunDao.query(qcs, ocs, -1, -1);
		List<FunctionVo> functionVos = new ArrayList<FunctionVo>();
		for(RoleFun roleFun : roleFuns){
			functionVos.add(po2Vo(roleFun.getFunction()));
		}
		
		return functionVos;
	}

}
