package cn.core.framework.service.impl;

import org.springframework.stereotype.Service;
import cn.core.framework.po.User;
import cn.core.framework.vo.UserVo;
import cn.core.framework.service.IUserService;
import cn.core.framework.common.service.BaseServiceImpl;

@Service("sys.userService")
public class UserServiceImpl extends BaseServiceImpl<UserVo,User> implements
		IUserService {
}
