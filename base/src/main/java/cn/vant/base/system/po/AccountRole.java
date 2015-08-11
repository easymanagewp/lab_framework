package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name = "sys_account_role")
@Table(name = "sys_account_role")
@Module(value="sys.account_role")
public class AccountRole extends Po<AccountRole> {
	
	private Account account;
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
