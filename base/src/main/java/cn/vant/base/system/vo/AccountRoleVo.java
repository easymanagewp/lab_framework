package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;
public class AccountRoleVo extends Vo<AccountRoleVo> {
	private static final long serialVersionUID = 1L;
	private AccountVo accountVo;
	private RoleVo roleVo;
	public AccountVo getAccountVo() {
		return accountVo;
	}
	public void setAccountVo(AccountVo accountVo) {
		this.accountVo = accountVo;
	}
	public RoleVo getRoleVo() {
		return roleVo;
	}
	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}


}
