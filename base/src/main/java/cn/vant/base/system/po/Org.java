package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.TreePo;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_org")
@Table(name="sys_org")
@Module(value="sys.org")
public class Org extends TreePo<Org>  {
	
	private String code;//组织编码
	private String describtion;// 说明
	
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}