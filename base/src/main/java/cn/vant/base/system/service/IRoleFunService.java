package cn.vant.base.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.vo.RoleFunVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:56:12 </strong> <br>
 * <strong>概要 : 角色-功能service</strong> <br>
 */
@Transactional
public interface IRoleFunService extends IBaseService<RoleFunVo> {
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:34:01 </strong> <br>
	 * <strong>概要 : 获取当前角色具备的功能列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param roleId 角色Id
	 *@return List
	 *@throws InvalidIdException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<RoleFunVo> list(String roleId) throws InvalidIdException, GlobalException;
	
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:34:37 </strong> <br>
	 * <strong>概要 : 得到当前（roleId）角色具备的当前（parentFunctionId）下的json字串（带有选中信息）</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param roleId  角色Id 
	 *@param checkedFunctionIds 选择的功能Ids 
	 *@param parentFunctionId 上级功能Id
	 *@return
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public StringBuffer getFunctionCheckTree(String roleId,String checkedFunctionIds, String parentFunctionId) throws GlobalException;

}
