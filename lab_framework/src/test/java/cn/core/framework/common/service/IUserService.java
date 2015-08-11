package cn.core.framework.common.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.vo.UserVo;

@Transactional
public interface IUserService extends IBaseService<UserVo>{

}
