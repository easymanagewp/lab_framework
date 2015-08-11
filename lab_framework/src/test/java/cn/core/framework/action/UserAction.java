package cn.core.framework.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.core.framework.vo.UserVo;
import cn.core.framework.common.action.RestAction;

@Controller("sys.userAction")
@RequestMapping("/sys/user")
public class UserAction extends RestAction<UserVo> {
}