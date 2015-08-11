package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;
public class AccountOrgVo extends Vo<AccountOrgVo> {
	
	private static final long serialVersionUID = 1L;
	private AccountVo accountVo;
	private OrgVo orgVo;
	public AccountVo getAccountVo() {
		return accountVo;
	}
	public void setAccountVo(AccountVo accountVo) {
		this.accountVo = accountVo;
	}
	public OrgVo getOrgVo() {
		return orgVo;
	}
	public void setOrgVo(OrgVo orgVo) {
		this.orgVo = orgVo;
	}

}
