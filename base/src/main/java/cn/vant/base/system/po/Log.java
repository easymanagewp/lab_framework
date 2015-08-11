package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;

@Entity(name="sys_log")
@Table(name="sys_log")
@Module(value="sys.log")
public class Log extends Po<Log> {
	private String busInfo;//业务模块
	private String subject;//访问地址
	private String function;//功能
	private String content;//模块
	private String type;//方法类型
	private String ip;//ip地址
	private String time;//操作时间
	private String user;//操作者
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}
	
}