package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.vant.base.system.vo.OrgVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:55:53 </strong> <br>
 * <strong>概要 : 组织service</strong> <br>
 */
@Transactional
public interface IOrgService extends IBaseService<OrgVo> {

}
