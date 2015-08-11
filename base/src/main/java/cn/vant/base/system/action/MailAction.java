package cn.vant.base.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.vant.base.system.service.IMailService;
import cn.vant.base.system.vo.MailVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:27:54 </strong> <br>
 * <strong>概要 : mail action</strong> <br>
 */
@Controller("sys.mailAction")
@RequestMapping("sys/mail")
@Logger(busInfo="系统管理",content="邮件/短信 管理")
public class MailAction extends BaseAction<MailVo>{
	final String VIEW_PATH = "/sys/mail/mail";
	
	@Autowired IMailService mailService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}

	@Override
	@Logger(type=LoggerType.Insert,function="添加邮件/短信")
	public ModelAndView add(MailVo vo, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		ModelAndView mav = super.add(vo,attr, request, response);
		mav.addObject("type", vo.getType());
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
	}
	
	@RequestMapping(value="sendMsg",produces="application/json;charset=utf-8")
	@Logger(type=LoggerType.Insert,function="发送邮件/短信")
	public @ResponseBody String sendMsg(MailVo vo)throws GlobalException {
		try {
			mailService.add(vo);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
}
