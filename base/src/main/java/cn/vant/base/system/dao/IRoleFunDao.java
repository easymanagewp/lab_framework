package cn.vant.base.system.dao;

import java.util.List;

import cn.core.framework.common.dao.IBaseDao;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.po.RoleFun;
/**
 * <strong>创建信息:2015年7月8日 下午12:48:24 </strong> <br>
 * <strong>概要 : 角色-功能 DAO</strong> <br>
 */
public interface IRoleFunDao extends IBaseDao<RoleFun>{
	/**
	 * <strong>创建信息:2015年7月8日 下午12:47:48 </strong> <br>
	 * <strong>概要 : 清空当前角具备的功能列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param roleId 角色Id
	 *@throws InvalidIdException
	 */
	public void deleteByRoleId(String roleId) throws InvalidIdException;
	/**
	 * <strong>创建信息:2015年7月8日 下午12:47:48 </strong> <br>
	 * <strong>概要 : 得到当前角具备的功能列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param roleId 角色Id
	 *@throws InvalidIdException
	 */
	public List<RoleFun> list(String roleId) throws InvalidIdException;
}
