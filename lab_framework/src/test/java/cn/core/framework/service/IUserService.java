package cn.core.framework.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.vo.UserVo;
import cn.core.framework.common.service.IBaseService;

@Transactional
public interface IUserService extends IBaseService<UserVo> {
	
}
