package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_role_fun")
@Table(name="sys_role_fun")
@Module(value="sys.user")
public class RoleFun extends Po<RoleFun> {
	
	private Role role;
	private Function function;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name="fun_id")
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
}