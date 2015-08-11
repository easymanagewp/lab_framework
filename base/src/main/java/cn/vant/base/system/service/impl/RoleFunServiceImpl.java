package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IFunctionDao;
import cn.vant.base.system.dao.IRoleFunDao;
import cn.vant.base.system.po.Function;
import cn.vant.base.system.po.RoleFun;
import cn.vant.base.system.service.IRoleFunService;
import cn.vant.base.system.vo.FunctionVo;
import cn.vant.base.system.vo.RoleFunVo;
import cn.vant.base.system.vo.RoleVo;

@Service("sys.roleFunService")
public class RoleFunServiceImpl extends BaseServiceImpl<RoleFunVo, RoleFun> implements
		IRoleFunService {

	@Autowired IFunctionDao functionDao;
	@Autowired IRoleFunDao roleFunDao;
	
	@Override
	public List<RoleFunVo> list(String roleId)
			throws InvalidIdException, GlobalException {
		List<RoleFun> pList = roleFunDao.list(roleId);
		List<RoleFunVo> vList = new ArrayList<RoleFunVo>();
		for(RoleFun p:pList){
			vList.add(po2Vo(p));
		}
		return vList;
	}
	
	@Override
	public RoleFunVo po2Vo(RoleFun p) throws GlobalException {
		RoleFunVo vo = super.po2Vo(p);
		vo.setFunctionVo(new FunctionVo().toVo(p.getFunction()));
		vo.setRoleVo(new RoleVo().toVo(p.getRole()));
		return vo;
	}

	@Override
	public StringBuffer getFunctionCheckTree(String roleId,String checkedFunctionIds,
			String parentFunctionId) throws GlobalException {
		StringBuffer treeBuffer = new StringBuffer();
		treeBuffer.append("[");
		List<Function> functionList =  functionDao.findByPid(parentFunctionId);
		if (functionList.size() > 0) {
			for (Function po : functionList) {
				List<Function> tempList =   functionDao.findByPid(po.getId());
				if (null!=tempList&&tempList.size() > 0)
					treeBuffer.append("{name:'" + po.getName() + "', treeNid:'" + po.getId() + "', isParent:" + true + ",checked:" + isHave(checkedFunctionIds, po.getId()) + ",children:" + getFunctionCheckTree( roleId,checkedFunctionIds, po.getId())+ "},");
				else
					treeBuffer.append("{name:'" + po.getName() + "', treeNid:'" + po.getId() + "', isParent:" + false + ",checked:" + isHave(checkedFunctionIds, po.getId()) + "},");
			}
			if (treeBuffer.length() > 0)
				treeBuffer.replace(treeBuffer.length() - 1, treeBuffer.length(), "");
		}
		treeBuffer.append("]");
		return treeBuffer;
	}
	
	private boolean isHave(String funIds, String funId) {
		boolean key = false;
		if (!StrUtils.isBlankOrNull(funIds)) {
			if (funIds != null && funIds.split(",").length > 0) {
				for (String id : funIds.split(",")) {
					if (id.equals(funId)) {
						key = true;
						break;
					}
				}
			}
		}
		return key;
	}
}
