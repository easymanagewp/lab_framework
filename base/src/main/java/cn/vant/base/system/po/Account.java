package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_account")
@Table(name="sys_account")
@Module(value="sys.account")
public class Account extends Po<Account> {
	
	private User user;//用户信息Id
	private Org org;//账户所属部门
	private String loginName;//登陆名称
	private String password;//登陆的密码
	private String status;//状态
	private Status isUsed = Status.Y;//是否可用
	private String signature;//签章
	
	public Status getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Status isUsed) {
		this.isUsed = isUsed;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	
	@ManyToOne
	@JoinColumn(name="org_id")
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
