package cn.vant.base.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.vo.UserVo;
/**
 * <strong>创建信息: 2015年7月8日 下午3:57:13 </strong> <br>
 * <strong>概要 : 用户service</strong> <br>
 */
@Transactional
public interface IUserService extends IBaseService<UserVo> {

	List<Map<String,Object>> getSimpleUsers(UserVo userVo, String roleId, String orgId, String dutyId) throws GlobalException;
}
