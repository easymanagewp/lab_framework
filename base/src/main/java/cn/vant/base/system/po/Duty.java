package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_duty")
@Table(name="sys_duty")
@Module(value="sys.duty")
public class Duty extends Po<Duty> {
	
	private String name;//角色名称
	private Status isUsed = Status.Y;//是否可用
	private String describtion;// 说明
	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Status isUsed) {
		this.isUsed = isUsed;
	}
	
}