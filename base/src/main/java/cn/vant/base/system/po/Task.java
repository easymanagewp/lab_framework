package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.ActionType;
import cn.core.framework.utils.code.CreateCodeUtils;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_task")
@Table(name="sys_task")
@Module(value="sys.task")
public class Task extends Po<Task>  {
	
	private User sender;//发件人
	private User reciver;//收件人
	private String title;//标题
	private String context;//内容
	private String uri;//链接
	private String flag;//状态
	private String bus;//业务模块
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	@ManyToOne
	@JoinColumn(name="reciver_id")
	public User getReciver() {
		return reciver;
	}
	public void setReciver(User reciver) {
		this.reciver = reciver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	public static void main(String[] args) {
		CreateCodeUtils.autoCreateCode(Task.class, false, ActionType.JSP);
	}

}