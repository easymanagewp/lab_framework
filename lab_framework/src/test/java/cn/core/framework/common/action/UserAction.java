package cn.core.framework.common.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.core.framework.common.vo.UserVo;

@Controller("user.action")
@RequestMapping("users")
public class UserAction extends RestAction<UserVo> {

}
