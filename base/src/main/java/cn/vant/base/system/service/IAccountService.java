package cn.vant.base.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.exception.PasswordNotMatchException;
import cn.vant.base.system.exception.UserNameNotFindException;
import cn.vant.base.system.vo.AccountVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:54:17 </strong> <br>
 * <strong>概要 : 账户service</strong> <br>
 */
@Transactional
public interface IAccountService extends IBaseService<AccountVo> {
	/**
	 * <strong>创建信息:2015年7月8日 上午10:31:41 </strong> <br>
	 * <strong>概要 : 获得当前用户所拥有的账户信息</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param userId 用户id
	 *@return list 
	 *@throws InvalidIdException
	 *@throws GlobalException
	 */
//	@Logger(log="查询用户%{getUserName-->param:p[1])}的所有账户信息")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<AccountVo> list(String userId) throws InvalidIdException, GlobalException;

//	@Logger(log="查询用户%{getUserName-->param:p[1])}的基本账户信息")
	List<Map<String,Object>> getSimpleListByUserId(String userId) throws GlobalException;
	
	public AccountVo login(String loginName,String password) throws UserNameNotFindException,PasswordNotMatchException, GlobalException;
}
