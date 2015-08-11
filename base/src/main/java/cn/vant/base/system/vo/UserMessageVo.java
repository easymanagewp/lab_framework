package cn.vant.base.system.vo;

import java.util.Date;

import cn.core.framework.common.po.Po.Status;
import cn.core.framework.common.vo.Vo;

public class UserMessageVo extends Vo<UserMessageVo> {
	private static final long serialVersionUID = 3755186231050703407L;
	
	/**
	 * 已被移动到回收站（已删除）
	 */
	public static final String POSITION_DEL = "0";
	/**
	 * 收到，可阅读，存在于收件箱内（未被删除）
	 */
	public static final String POSITION_READABLE = "1";
	
	private Status readFlag = Status.N;	//	是否已阅读
	private Date readTime;				//	阅读时间
	private String position;			//	0已删除 2收件箱   
	private MessageVo message;			// 	邮件主体
	private AccountVo accountVo;		//	接收账户
	
	/**
	 * 获取邮件阅读状态
	 * @return 
	 * Po.Status.N 尚未阅读<br>
	 * Po.Status.Y 已经阅读<br>	
	 */
	public Status getReadFlag() {
		return readFlag;
	}
	/**
	 * 设置邮件月度状态
	 * @param readFlag
	 * Po.Status.N 尚未阅读<br>
	 * Po.Status.Y 已经阅读<br>
	 */
	public void setReadFlag(Status readFlag) {
		this.readFlag = readFlag;
	}
	
	/**
	 * 获取邮件被阅读的时间
	 * @return 邮件被阅读的时间
	 */
	public Date getReadTime() {
		return readTime;
	}
	/**
	 * 设置邮件被阅读的时间
	 * @param readTime 邮件被阅读的时间
	 */
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	/**
	 * 获取邮件状态
	 * @return
	 * UserMessage.POSITION_DEL 已被移动到回收站（已删除）<br>
	 * UserMessage.POSITION_READABLE 收到，可阅读，存在于收件箱内（未被删除）
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置邮件状态
	 * @param position
	 * UserMessage.POSITION_DEL 已被移动到回收站（已删除）<br>
	 * UserMessage.POSITION_READABLE 收到，可阅读，存在于收件箱内（未被删除）
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取邮件主体信息
	 * @return 邮件主体信息
	 */
	public MessageVo getMessageVo() {
		return message;
	}
	/**
	 * 设置邮件主体信息
	 * @param message 邮件主体信息
	 */
	public void setMessageVo(MessageVo message) {
		this.message = message;
	}
	
	/**
	 * 获取收件账户信息
	 * @return 收件账户信息
	 */
	public AccountVo getAccountVo() {
		return accountVo;
	}
	/**
	 * 设置收件账户信息
	 * @param user 收件账户信息
	 */
	public void setAccountVo(AccountVo account) {
		this.accountVo = account;
	}
}

