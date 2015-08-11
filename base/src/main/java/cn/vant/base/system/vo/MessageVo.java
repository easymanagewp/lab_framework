package cn.vant.base.system.vo;

import java.util.Date;
import java.util.List;

import cn.core.framework.common.vo.Vo;
import cn.vant.base.system.po.Message.Type;


public class MessageVo extends Vo<MessageVo>{
	private static final long serialVersionUID = 1L;
		
	private Date sendTime;				//发送时间
	private String position;			// 0 已删除 1 草稿箱 3 发件箱
	private Type type;					// 0-消息 1-公告
	
	private String subject;//主题
	private String content;//内容
	private AccountVo senderVo;//发送人
	
	private List<UserMessageVo> targetUserVoList;
	private List<AccountVo> targetAccountVos;
	
	
	public List<AccountVo> getTargetAccountVos() {
		return targetAccountVos;
	}
	public void setTargetAccountVos(List<AccountVo> targetAccounts) {
		this.targetAccountVos = targetAccounts;
	}
	
	/**
	 * 获取邮件状态
	 * @return
	 * Message.PISITION_DEL 已删除<br>
	 * Message.POSITION_SAVE 已存草稿<br>
	 * Message.POSITION_SEND 已发送
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置邮件状态
	 * @param position
	 * Message.PISITION_DEL 已删除<br>
	 * Message.POSITION_SAVE 已存草稿<br>
	 * Message.POSITION_SEND 已发送
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取邮件主题（标题）
	 * @return 邮件主题
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * 设置邮件主题（标题）
	 * @param subject 邮件主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 获取邮件类型
	 * @return
	 * Type.MSG 邮件<br>
	 * Type.ADV 公告<br>
	 */
	public Type getType() {
		return type;
	}
	/**
	 * 设置邮件类型
	 * @param type
	 * Type.MSG 邮件<br>
	 * Type.ADV 公告<br>
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * 获取邮件内容，为一个富文本
	 * @return 邮件内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置邮件内容，为一个富文本
	 * @param content 邮件内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取邮件发送时间
	 * @return 邮件发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * 设置邮件发送时间
	 * @param sendTime 邮件发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	/**
	 * 获取邮件发送账户信息
	 * @return 邮件发送账户信息
	 */
	public AccountVo getSenderVo() {
		return senderVo;
	}
	/**
	 * 设置邮件发送账户信息
	 * @param sender 邮件发送账户信息，如需设置可直接从缓存中直接获取发送人账户信息，从而获取当前登录账户的id，之后通过id获取账户信息，进行设置。<br>
	 * 可通过 GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY) 获取当前登录账户信息。缓存对象为AccountVo
	 */
	public void setSenderVo(AccountVo senderVo) {
		this.senderVo = senderVo;
	}
	
	/**
	 * 获取邮件接收人集合
	 * @return 邮件接收人集合
	 */
	public List<UserMessageVo> getTargetUserVoList() {
		return targetUserVoList;
	}
	/**
	 * 设置邮件接收人的集合
	 * @param targetUserVoList 邮件接收人的集合
	 */
	public void setTargetUserVoList(List<UserMessageVo> targetUserVoList) {
		this.targetUserVoList = targetUserVoList;
	}
	
	
	
}
