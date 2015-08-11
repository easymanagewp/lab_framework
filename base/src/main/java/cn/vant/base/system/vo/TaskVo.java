package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;

public class TaskVo extends Vo<TaskVo> {
	private static final long serialVersionUID = 1L;
	private UserVo sender;//发件人
	private UserVo reciver;//收件人
	private String title;//标题
	private String context;//内容
	private String uri;//链接
	private String flag;//状态
	private String bus;//业务模块
	
	public UserVo getSender() {
		return sender;
	}
	public void setSender(UserVo sender) {
		this.sender = sender;
	}
	public UserVo getReciver() {
		return reciver;
	}
	public void setReciver(UserVo reciver) {
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
	
	
}

