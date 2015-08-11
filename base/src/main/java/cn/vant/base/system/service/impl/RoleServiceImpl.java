package cn.vant.base.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.BeanUtils;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.IAccountRoleDao;
import cn.vant.base.system.dao.IFunctionDao;
import cn.vant.base.system.dao.IRoleDao;
import cn.vant.base.system.dao.IRoleFunDao;
import cn.vant.base.system.po.Role;
import cn.vant.base.system.po.RoleFun;
import cn.vant.base.system.service.IRoleService;
import cn.vant.base.system.vo.RoleVo;

@Service("sys.roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleVo, Role> implements
		IRoleService {
		
	@Autowired IRoleDao roleDao;
	@Autowired IRoleFunDao roleFunDao;
	@Autowired IFunctionDao functionDao;
	@Autowired IAccountRoleDao accountRoleDao;
	
	@Override
	public void add(RoleVo v) throws GlobalException {
		Role role = vo2Po(v);
		saveRoleFun(role, v.getFunctionIds());
		
		roleDao.add(role);
	}
	
	@Override
	public void update(RoleVo v) throws InvalidIdException,
			EntityNotFindException, GlobalException {
		if(StrUtils.isBlankOrNull(v.getId())) throw new InvalidIdException();
		Role role = roleDao.findById(v.getId());
		if(null==role) throw new EntityNotFindException();
		BeanUtils.copyProperties(v, role, new String[]{"id"});
		
		roleDao.update(role);
		
		//处理权限信息
		saveRoleFun(role, v.getFunctionIds());
	}

	
	private void saveRoleFun(Role role,String functionIds) throws GlobalException{
		Map<String, RoleFun> map = roleFunMap(role);
		RoleFun roleFun = null;
		for(String id:StrUtils.split(functionIds, ",")){
			if(null != map && null!=map.get(id)){//不变的权限
				map.remove(id);
				continue;
			}else{//新加的权限
				roleFun = new RoleFun();
				roleFun.setRole(role);
				roleFun.setFunction(functionDao.findById(id));
				roleFunDao.add(roleFun);
			}
		}
		//去掉的权限
		if(null != map){
			for(String key:map.keySet()){
				roleFun = map.get(key);
	//			List<Favorite> favoriteList = favoriteDao.listByRoleFunId(roleFun.getId());
				//当前未被用户收藏,则真删
	//			if(null==favoriteList||favoriteList.size()==0)
					roleFunDao.delete(roleFun);
				//当前被用户收藏,则置收藏信息为不可用
	//			for(Favorite favorite : favoriteList){
	//				String message = "去掉"+favorite.getRoleFun().getFunction().getName()+","+DateUtils.getCurrDateTime();
	//				favorite.setIsUsed(Status.N);
	//				favorite.setMessage(message);
	//				favoriteDao.update(favorite);
	//			}
				//置权限信息为删除
	//			roleFun.setIsDel(Status.Y);
	//			roleFunDao.update(roleFun);
			} 
		}
	}

	private Map<String, RoleFun> roleFunMap(Role role) {
		List<RoleFun> roleFunList = null;
		Map<String,RoleFun> map = null;
		try {
			roleFunList = roleFunDao.list(role.getId());
		} catch (InvalidIdException e) {
			log.debug("获取角色权限信息出错",e);
		}
		if(null==roleFunList||roleFunList.size()==0)
			return map;
		
		map = new HashMap<String,RoleFun>();
		for(RoleFun roleFun:roleFunList){
			map.put(roleFun.getFunction().getId(), roleFun);
		}
		return map;
	}
}
