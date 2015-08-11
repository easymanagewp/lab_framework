package cn.vant.base.system.vo;

import org.springframework.web.multipart.MultipartFile;

import cn.core.framework.common.vo.Vo;

public class TemplateVo extends Vo<TemplateVo> {
	private static final long serialVersionUID = 1L;
	private String type;//标记模板类别（导入模板/导出模板）
	private String name;//模板名称
	private String templateName;//模板编码
	private String user;//负责人
	private String versionNo;//版本号
	private String note;//说明
	private String busInfo;//业务模块
	private String path;//存储路径
	private MultipartFile file;//模板文件
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}

