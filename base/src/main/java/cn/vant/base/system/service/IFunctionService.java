package cn.vant.base.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.vo.FunctionVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:54:54 </strong> <br>
 * <strong>概要 : 功能service</strong> <br>
 */
@Transactional
public interface IFunctionService extends IBaseService<FunctionVo> {

	public List<FunctionVo> findUserFunctions(String accountId) throws GlobalException;
	
}
