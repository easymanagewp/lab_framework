package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.TreePo;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_function")
@Table(name="sys_function")
@Module(value="sys.function")
public class Function extends TreePo<Function>  {
	private String code; // 功能编码
	private String wfCode; //流程编码
	private String url;// 功能路径
	private String imageUrl;
	private String type;// 功能类型
	private Status isUsed = Status.Y;// 是否可用
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Status getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Status isUsed) {
		this.isUsed = isUsed;
	}

	public String getWfCode() {
		return wfCode;
	}

	public void setWfCode(String wfCode) {
		this.wfCode = wfCode;
	}

	

}