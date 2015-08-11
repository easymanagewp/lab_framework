package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.vant.base.system.vo.RoleVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:56:26 </strong> <br>
 * <strong>概要 : 角色service</strong> <br>
 */
@Transactional
public interface IRoleService extends IBaseService<RoleVo> {

}
