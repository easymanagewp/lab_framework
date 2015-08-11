package cn.vant.base.system.dao;

import java.util.List;

import cn.core.framework.common.dao.IBaseDao;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.po.Code;
/**
 * <strong>创建信息:2015年7月8日 下午12:45:09 </strong> <br>
 * <strong>概要 : 公告代码DAO</strong> <br>
 */
public interface ICodeDao extends IBaseDao<Code>{
	/**
	 * <strong>创建信息:2015年7月8日 下午12:45:16 </strong> <br>
	 * <strong>概要 : 根据业务模块和公共代码code返回公共代码列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务模块代码
	 *@param code 公共代码code 
	 *@return List 公告代码列表
	 *@throws GlobalException
	 */
	public List<String> list(String busInfo, String code) throws GlobalException;
	/**
	 * <strong>创建信息:2015年7月8日 下午12:46:03 </strong> <br>
	 * <strong>概要 : 根据业务模块代码和公共代码获取公共代码对象</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务模块代码
	 *@param code 公共代码code 
	 *@return Code
	 *@throws GlobalException
	 */
	public Code find(String busInfo, String code) throws GlobalException;
	/**
	 * <strong>创建信息:2015年7月8日 下午12:46:58 </strong> <br>
	 * <strong>概要 : 更新公共代码（追加公共代码内容）</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务模块代码
	 *@param code 公共代码code 
	 *@param appendContent 追加内容
	 *@return
	 *@throws GlobalException
	 */
	public boolean update(String busInfo, String code, String appendContent) throws GlobalException;
}
