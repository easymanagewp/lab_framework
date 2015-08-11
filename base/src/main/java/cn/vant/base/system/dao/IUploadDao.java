package cn.vant.base.system.dao;

import java.util.List;

import cn.core.framework.common.dao.IBaseDao;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.po.Upload;
/**
 * <strong>创建信息:2015年7月8日 下午12:49:58 </strong> <br>
 * <strong>概要 :附件DAO </strong> <br>
 */
public interface IUploadDao extends IBaseDao<Upload>{
	/**
	 * <strong>创建信息:2015年7月8日 下午12:50:05 </strong> <br>
	 * <strong>概要 : 根据路径获取附件对象</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param path
	 *@return Upload 
	 *@throws GlobalException
	 */
	public Upload findByPath(String path) throws GlobalException;
	/**
	 * <strong>创建信息:2015年7月8日 下午12:50:18 </strong> <br>
	 * <strong>概要 : 根据业务代码获取附件列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务代码
	 *@param busId 业务代码
	 *@return List
	 *@throws GlobalException
	 */
	public List<Upload> find(String busInfo,String busId) throws GlobalException;
}
