package cn.vant.base.system.dao;

import java.util.List;

import cn.core.framework.common.dao.IBaseDao;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.po.AccountRole;
/**
 * <strong>创建信息:2015年7月8日 下午12:44:13 </strong> <br>
 * <strong>概要 : 账户-角色 DAO</strong> <br>
 */
public interface IAccountRoleDao extends IBaseDao<AccountRole>{
	/**
	 * <strong>创建信息:2015年7月8日 下午12:43:51 </strong> <br>
	 * <strong>概要 : 清楚当前账户具备的角色信息</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param accountId 账户id
	 *@throws InvalidIdException
	 */
	public void deleteByAccountId(String accountId) throws InvalidIdException;
	/**
	 * <strong>创建信息:2015年7月8日 下午12:43:51 </strong> <br>
	 * <strong>概要 : 得到当前账户具备的角色信息</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param accountId 账户id
	 *@throws InvalidIdException
	 */
	public List<AccountRole> listByAccountId(String accountId)
			throws InvalidIdException, GlobalException;
	/**
	 * <strong>创建信息:2015年7月8日 下午12:43:51 </strong> <br>
	 * <strong>概要 : 得到当前角色具备的账户信息</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param accountId 账户id
	 *@throws InvalidIdException
	 */
	public List<AccountRole> listByRoleId(String roleId)
			throws InvalidIdException, GlobalException;
}
