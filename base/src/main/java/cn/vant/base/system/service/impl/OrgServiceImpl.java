package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.BeanUtils;
import cn.core.framework.utils.CollectionUtils;
import cn.vant.base.system.dao.IOrgDao;
import cn.vant.base.system.po.Org;
import cn.vant.base.system.service.IOrgService;
import cn.vant.base.system.vo.OrgVo;

@Service("sys.orgService")
public class OrgServiceImpl extends BaseServiceImpl<OrgVo, Org> implements
		IOrgService {
	
	@Autowired IOrgDao orgDao;
	
	@Override
	public void add(OrgVo v) throws GlobalException {
		Org po = vo2Po(v);
		Org org = orgDao.findById(v.getParentVo().getId());
		po.setParent(org);
		orgDao.add(po);
	}
	
	@Override
	public void update(OrgVo v) throws InvalidIdException,
			EntityNotFindException, GlobalException {
		Org org = orgDao.findById(v.getId());
		BeanUtils.copyProperties(v, org, new String[]{"id"});
	
		Org parent = orgDao.findById(v.getParentVo().getId());
		org.setParent(parent);
		
		orgDao.update(org);
	}
	@Override
	@SuppressWarnings("deprecation")
	public PageResult pageResult(OrgVo v, PageResult pageResult)
			throws GlobalException {
		List<QueryCondition> queryList = pageResult.getQueryList();
		if(CollectionUtils.isEmpty(queryList))
			queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("parent.id",QueryCondition.EQ,v.getPid()));
		pageResult.setQueryList(queryList);
		return super.pageResult(v, pageResult);
	}
	
	@Override
	public OrgVo po2Vo(Org p) throws GlobalException {
		OrgVo vo = super.po2Vo(p);
		if(null!=p.getParent())
			vo.setPid(p.getParent().getId());
		return vo;
	}
}
