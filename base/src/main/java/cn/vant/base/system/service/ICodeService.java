package cn.vant.base.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.vo.CodeVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:54:27 </strong> <br>
 * <strong>概要 : 公共代码service</strong> <br>
 */
@Transactional
public interface ICodeService extends IBaseService<CodeVo> {
	/**
	 * <strong>创建信息:2015年7月8日 上午10:32:20 </strong> <br>
	 * <strong>概要 : 根据业务模块和公共代码参数获取公共代码列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务模块代码
	 *@param code 公共代码code
	 *@return list
	 *@throws GlobalException
	 */
	public List<String> list(String busInfo,String code) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:05:55 </strong> <br>
	 * <strong>概要 : 追加公共代码数据</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务代码模块
	 *@param code 公共代码code
	 *@param appendContent 追加内容
	 *@return boolean
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(String busInfo, String code,String appendContent) throws GlobalException;
}
