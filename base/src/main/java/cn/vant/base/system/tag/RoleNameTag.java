package cn.vant.base.system.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.core.framework.cache.GlobalCache;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.vo.AccountRoleVo;

public class RoleNameTag extends TagSupport{

	private static final long serialVersionUID = 7245719403145604727L;

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();  
		@SuppressWarnings("unchecked")
		List<AccountRoleVo> account = (List<AccountRoleVo>) GlobalCache.getInstance().get(request, Constant.User.USER_ROLE_INFO);
		String roleName = account.get(0).getRoleVo().getName();
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(roleName);
		} catch (IOException e) {
		}
		return super.doStartTag();
	}
}