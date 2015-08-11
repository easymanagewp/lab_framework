package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;

public class CodeVo extends Vo<CodeVo> {
	
	private static final long serialVersionUID = 1L;
	private String busInfo;//业务模块
	private String name;//类型
	private String code;//类型
	private String content;//值
	private String describtion;//说明
	
	public String getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}