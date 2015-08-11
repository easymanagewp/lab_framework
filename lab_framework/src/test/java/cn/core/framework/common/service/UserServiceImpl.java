package cn.core.framework.common.service;

import org.springframework.stereotype.Service;

import cn.core.framework.common.po.User;
import cn.core.framework.common.vo.UserVo;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserVo, User> implements
		IUserService {

}
