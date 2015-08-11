package cn.vant.base.system.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.core.framework.cache.GlobalCache;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.vo.AccountVo;

public class UserNameTag extends TagSupport{

	private static final long serialVersionUID = 7245719403145604727L;

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();  
		AccountVo account = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		String userName = account.getUserVo().getName();
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(userName);
		} catch (IOException e) {
		}
		return super.doStartTag();
	}
}
