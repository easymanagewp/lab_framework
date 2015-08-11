package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.ActionType;
import cn.core.framework.utils.code.CreateCodeUtils;
import cn.core.framework.utils.code.annotation.Module;

/**
 * <strong>创建信息:</strong>Roy Wang 2015年7月27日 下午1:54:11  <br>
 * <strong>概要 : </strong> <br>
 * 收藏列表
 */
@Entity(name="sys_favorite")
@Table(name="sys_favorite")
@Module(value="sys.favorite")
public class Favorite extends Po<Favorite> {
	
	private Account account; 			//账户
	private Function function;			//功能
	private String alias;				//别名
	private Status isUsed = Status.Y;	//是否可用
	private String message;				//当权限被去掉时的消息
	@ManyToOne
	@JoinColumn(name="account_id")
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@ManyToOne
	@JoinColumn(name="fun_id")
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	
	
	public Status getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Status isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static void main(String[] args) {
		CreateCodeUtils.autoCreateCode(Favorite.class, true, ActionType.JSP);
	}
	
	@Override
	public int hashCode() {
		String functionId = this.getFunction()==null?"":this.getFunction().getId();
		String accountId = this.getAccount()==null?"":this.getAccount().getId();
		return functionId+accountId == ""?super.hashCode():(functionId+accountId).hashCode();
	}
}
