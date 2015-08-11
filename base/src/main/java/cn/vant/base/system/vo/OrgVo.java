package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;

public class OrgVo extends Vo<OrgVo>  {
	
	private static final long serialVersionUID = 1L;
	private OrgVo parentVo;//上级组织
	private String name;//组织名称
	private String code;//组织编码
	private String describtion;// 说明
	
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	
	public OrgVo getParentVo() {
		return parentVo;
	}
	public void setParentVo(OrgVo parentVo) {
		this.parentVo = parentVo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}