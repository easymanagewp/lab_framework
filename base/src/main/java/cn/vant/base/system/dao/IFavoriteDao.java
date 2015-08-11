package cn.vant.base.system.dao;

import java.util.List;

import cn.core.framework.common.dao.IBaseDao;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.po.Favorite;

public interface IFavoriteDao extends IBaseDao<Favorite> {
	/**
	 * <strong>创建信息:2015年7月23日 下午7:35:34 </strong> <br>
	 * <strong>概要 : 根据角色权限得到 用户收藏菜单</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param roleFunId
	 *@return
	 *@throws InvalidIdException
	 */
	public List<Favorite> listByRoleFunId(String roleFunId);
	/**
	 * <strong>创建信息:2015年7月23日 下午7:35:36 </strong> <br>
	 * <strong>概要 : 根据账户得到 用户收藏菜单</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param accountId
	 *@return
	 *@throws InvalidIdException
	 */
	public List<Favorite> listByAccountId(String accountId);
	
	/**
	 * <strong>创建信息:2015年7月23日 下午7:35:34 </strong> <br>
	 * <strong>概要 : 根据角色权限得到 用户收藏菜单</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param roleFunId
	 *@return
	 *@throws InvalidIdException
	 */
	public void deleteByRoleFunId(String roleFunId) ;
	/**
	 * <strong>创建信息:2015年7月23日 下午7:35:36 </strong> <br>
	 * <strong>概要 : 根据账户得到 用户收藏菜单</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param accountId
	 *@return
	 *@throws InvalidIdException
	 */
	public void deleteByAccountId(String accountId) ;
}

