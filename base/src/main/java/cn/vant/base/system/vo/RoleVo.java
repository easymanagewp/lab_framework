package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;

public class RoleVo extends Vo<RoleVo> {
	
	private static final long serialVersionUID = 1L;
	private String name;//角色名称
	private String isUsed;//是否可用
	private String describtion;// 说明
	private String functionIds;
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
	
	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	

}