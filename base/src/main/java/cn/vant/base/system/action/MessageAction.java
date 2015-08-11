package cn.vant.base.system.action;

import java.beans.PropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.common.action.BaseAction;
import cn.core.framework.common.action.Status;
import cn.core.framework.common.page.PageResult;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.po.Message;
import cn.vant.base.system.service.IMessageService;
import cn.vant.base.system.service.IUserMessageService;
import cn.vant.base.system.service.IUserService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.MessageVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:17 </strong> <br>
 * <strong>概要 : 站内信action</strong> <br>
 */
@Controller("sys.messageAction")
@RequestMapping("sys/message")
@Logger(busInfo="系统管理",content="站内信管理")
public class MessageAction extends BaseAction<MessageVo>{
	final String VIEW_PATH = "/sys/message/message";
	
	/**
	 * 回收站
	 */
	final String PAGE_HUI_SHOU_ZHAN = VIEW_PATH + "_0_page";
	/**
	 * 草稿箱
	 */
	final String PAGE_ROUGH_DRAFT 	= VIEW_PATH + "_1_page";
	/**
	 * 发件箱
	 */
	final String PAGE_OUTBOX 		= VIEW_PATH + "_2_page";
	/**
	 * 收件箱
	 */
	final String PAGE_INBOX 		= VIEW_PATH + "_3_page";
	/**
	 * 公告页面位置
	 */
	final String PAGE_GONG_GAO 		= VIEW_PATH + "_4_page";
	
	@Autowired IMessageService messageService;
	@Autowired IUserMessageService userMessageService;
	@Autowired IUserService userService;
	@Override
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@Override
	public ModelAndView edit(MessageVo v, HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		ModelAndView mv = super.edit(v, request, response);
		return mv;
	}	
	
	/**
	 * 初始化Action信息<br>
	 * 1：绑定属性转换器,Message.Type 转换
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception{
		binder.registerCustomEditor(Message.Type.class,"type", new MessageTypeEditor());
	}
	
	/*
	 * 保存站内信到草稿箱
	 */
	@Logger(type=LoggerType.Insert,function="保存站内信-->草稿箱")
	@RequestMapping(value="save2rd",method=RequestMethod.POST)
	public ModelAndView save2rd(MessageVo v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		this.messageService.save2Rd(v,accountVo);
		mav.setViewName("redirect:rough_draft_page.do");
		return mav;
	}
	
	/*
	 * 草稿箱，更新
	 */
	@Logger(type=LoggerType.Update,function="更新站内信-->草稿箱")
	@RequestMapping(value="update2rd",method=RequestMethod.POST)
	public ModelAndView update2rd(MessageVo v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		this.messageService.update2Rd(v,accountVo);
		mav.setViewName("redirect:rough_draft_page.do");
		return mav;
	}
	
	/*
	 * 发送邮件
	 */
	@Logger(type=LoggerType.Insert,function="发送邮件")
	@RequestMapping(value="send",method=RequestMethod.POST)
	public ModelAndView sendMessage(MessageVo v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		this.messageService.send(v,accountVo);
		// 发送完成后，进入发件箱
		mav.setViewName("redirect:outbox_page.do");
		return mav;
	}
	
	/**
	 * 进入收件箱，获取信件信息
	 */
	@RequestMapping(value="inbox_page")
	public ModelAndView inBoxPage(PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException{
		ModelAndView mav = new ModelAndView();
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		pageResult = this.userMessageService.findInBox(pageResult,accountVo);
		mav.addObject("pageResult", pageResult);
		mav.setViewName(PAGE_INBOX);
		return mav;
	}
	
	/**
	 * 进入发件箱，获取发件箱信息
	 */
	@RequestMapping(value="outbox_page")
	public ModelAndView outBoxPage(PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = new ModelAndView();
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		pageResult = this.messageService.findOutBox(pageResult,accountVo);
		mav.addObject("pageResult", pageResult);
		mav.setViewName(PAGE_OUTBOX);
		return mav;
	}
	
	/**
	 * 进入公告页面，公告信息
	 */
	@RequestMapping(value="gonggao_page")
	public ModelAndView gongGaoPage(PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = new ModelAndView(PAGE_GONG_GAO);
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		pageResult = this.userMessageService.findGongGao(pageResult,accountVo);
		mav.addObject("pageResult", pageResult);
		return mav;
	}
	
	/**
	 * 进入草稿箱
	 */
	@RequestMapping(value="rough_draft_page")
	public ModelAndView roughDraftPage(PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = new ModelAndView(PAGE_ROUGH_DRAFT);
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		pageResult = this.messageService.findRoughDraft(pageResult,accountVo);
		mav.addObject("pageResult",pageResult);
		return mav;
	}
	
	/**
	 * 进入回收站 
	 */
	@RequestMapping(value="hui_shou_zhan_page")
	public ModelAndView huiShouZhanPage(PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = new ModelAndView(PAGE_HUI_SHOU_ZHAN);
		AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
		pageResult = this.userMessageService.findDeletedMessage(pageResult,accountVo);
		mav.addObject("pageResult",pageResult);
		return mav;
	}
	
	/**
	 * 删除站内信草稿
	 */
	@Override
	@Logger(type=LoggerType.Insert,function="删除站内信")
	public ModelAndView update2del(MessageVo v, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		ModelAndView mv = super.update2del(v, attr, request, response);
		mv.setViewName("redirect:rough_draft_page.do");
		return mv;
	}

	@RequestMapping(value="update2read")
	@Logger(type=LoggerType.Update,function="更新为已读")
	public ModelAndView update2read(MessageVo v,RedirectAttributes attr)  throws GlobalException{
		Status status = new Status();
		status.setInfo("消息已读");
		try {
			messageService.update2read(v.getId());
			status.setStatus("已读信息回执成功");
		} catch (InvalidIdException e) {
			log.info("操作失败",e);
			status.setStatus("操作失败");
			status.setMessage("无效的ID"+ e.getMessage());
		} catch (EntityNotFindException e) {
			log.info("操作失败",e);
			status.setStatus("操作失败");
			status.setMessage("未获得有效实体"+ e.getMessage());
		} catch (GlobalException e) {
			log.info("操作失败",e);
			status.setStatus("操作失败");
			status.setMessage("无效的ID"+ e.getMessage());
		}
		attr.addFlashAttribute(STATUS, status);
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", v.getId());
		mav.setViewName(REDIRECT_2_SHOW);
		return mav;
	}
	
	@RequestMapping(value="update2freeze")
	@Logger(type=LoggerType.Update,function="冻结")
	public ModelAndView update2freeze(MessageVo v,RedirectAttributes attr)  throws GlobalException{
//		Status status = new Status();
//		status.setInfo("修改记录");
//		try {
////			messageService.update2freeze(v.getFreeze(),v.getFlag(), v.getId());
//			status.setStatus("操作成功");
//		} catch (InvalidIdException e) {
//			log.info("操作失败",e);
//			status.setStatus("操作失败");
//			status.setMessage("无效的ID"+ e.getMessage());
//		} catch (EntityNotFindException e) {
//			log.info("操作失败",e);
//			status.setStatus("操作失败");
//			status.setMessage("未获得有效实体"+ e.getMessage());
//		} catch (GlobalException e) {
//			log.info("操作失败",e);
//			status.setStatus("操作失败");
//			status.setMessage("无效的ID"+ e.getMessage());
//		}
//		attr.addFlashAttribute(STATUS, status);
//		
		ModelAndView mav = new ModelAndView();
		mav.addObject("position", v.getPosition());
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
	}
	
	static class MessageTypeEditor extends PropertyEditorSupport{
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if(text.equals("0")){
				setValue(Message.Type.MSG);
			}else{
				setValue(Message.Type.ADV);
			}
		}
		
		@Override
		public String getAsText() {
			if(getValue().equals(Message.Type.MSG)){
				return "0";
			}else{
				return "1";
			}
		}
	}
}
