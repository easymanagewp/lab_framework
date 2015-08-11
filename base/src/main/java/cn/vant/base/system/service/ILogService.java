package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.vant.base.system.vo.LogVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:55:06 </strong> <br>
 * <strong>概要 : 日志service</strong> <br>
 */
@Transactional
public interface ILogService extends IBaseService<LogVo> {

}
