package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;

public class FavoriteVo extends Vo<FavoriteVo> {
	
	private static final long serialVersionUID = 1L;
	private AccountVo accountVo; //账户
	private FunctionVo functionVo;//功能
	private String alias;//别名
	private String isUsed;//是否可用
	private String message;//当权限被去掉时的消息
	public AccountVo getAccountVo() {
		return accountVo;
	}
	public void setAccountVo(AccountVo accountVo) {
		this.accountVo = accountVo;
	}
	
	public FunctionVo getFunctionVo() {
		return functionVo;
	}
	public void setFunctionVo(FunctionVo functionVo) {
		this.functionVo = functionVo;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		String functionId = this.getFunctionVo()==null?"":this.getFunctionVo().getId();
		String accountId = this.getAccountVo()==null?"":this.getAccountVo().getId();
		return functionId+accountId == ""?super.hashCode():(functionId+accountId).hashCode();
	}
	
}

