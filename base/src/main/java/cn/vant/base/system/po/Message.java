package cn.vant.base.system.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;

@Entity(name="sys_message")
@Table(name="sys_message")
@Module(value="sys.message")
public class Message extends Po<Message>{
	public enum Type {
		MSG("消息"),ADV("公告");
		private String str;
		private Type(String str){
		this.str = str;
		}
		@Override
		public String toString() {
			return str;
		}
	}
	/**
	 * 邮件状态：已删除（发件方）
	 */
	public static final String PISITION_DEL = "0";	
	/**
	 * 邮件状态：草稿箱（发件方）
	 */
	public static final String POSITION_SAVE = "1";
	/**
	 * 邮件状态：已发送（发件方）
	 */
	public static final String POSITION_SEND = "2";	
	
//	public static final String POSITION_RECIVE = POSITION_SEND;//收件箱
	
	private Date sendTime;			//发送时间
	private String position;			// 0 已删除 1 草稿箱 3 发件箱
	private Type type;					// 0-消息 1-公告
	
	private String subject;//主题
	private String content;//内容
	private Account sender;//发送人
	
	private List<Account> targetAccounts = new ArrayList<Account>();	// 目标发送账户，待发送，用于保存草稿
	
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
	@Lob
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
	 * 获取邮件发送账户
	 * @return 邮件发送帐户
	 */
	@ManyToOne
	@JoinColumn(name = "send_account_id")
	public Account getSender() {
		return sender;
	}
	/**
	 * 设置邮件发送账户
	 * @param sender 邮件发送账户，如需设置可直接从缓存中直接获取发送人账户信息，从而获取当前账户的id，之后通过id获取账户信息，进行设置。<br>
	 * 可通过 GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY) 获取当前登录人账户信息。缓存对象为AccountVo
	 */
	public void setSender(Account sender) {
		this.sender = sender;
	}
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="sys_message_target_account",joinColumns={@JoinColumn(name="message_id")},inverseJoinColumns={@JoinColumn(name="account_id")})
	public List<Account> getTargetAccounts() {
		return targetAccounts;
	}
	public void setTargetAccounts(List<Account> targetAccounts) {
		this.targetAccounts = targetAccounts;
	}
	
	
	
}
