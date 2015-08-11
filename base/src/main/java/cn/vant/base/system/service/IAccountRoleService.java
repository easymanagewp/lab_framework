package cn.vant.base.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.vo.AccountRoleVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:54:00 </strong> <br>
 * <strong>概要 : 账户-角色service </strong> <br>
 */
@Transactional
public interface IAccountRoleService extends IBaseService<AccountRoleVo> {
	/**
	 * <strong>创建信息:2015年7月8日 上午10:30:39 </strong> <br>
	 * <strong>概要 : 获得当前账户所具备的角色信息</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param accountId 账户id
	 *@return list 
	 *@throws InvalidIdException
	 *@throws GlobalException
	 */
//	@Logger(log="查询与%{getAccountName-->param:p[1])}账户所对应的角色列表")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<AccountRoleVo> list(String accountId) throws InvalidIdException, GlobalException;

}
