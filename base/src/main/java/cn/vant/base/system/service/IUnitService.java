package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.vant.base.system.vo.UnitVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:56:37 </strong> <br>
 * <strong>概要 : 单位信息</strong> <br>
 */
@Transactional
public interface IUnitService extends IBaseService<UnitVo> {

}
