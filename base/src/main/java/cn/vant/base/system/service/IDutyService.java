package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.vant.base.system.vo.DutyVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:54:36 </strong> <br>
 * <strong>概要 : 岗位service</strong> <br>
 */
@Transactional
public interface IDutyService extends IBaseService<DutyVo> {
}
