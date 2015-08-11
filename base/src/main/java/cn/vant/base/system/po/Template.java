package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.ActionType;
import cn.core.framework.utils.code.CreateCodeUtils;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_template")
@Table(name="sys_template")
@Module(value="sys.template")
public class Template extends Po<Template> {
	public static final String EXPORT = "export";
	public static final String IMPORT = "import";
	private String type;//标记模板类别（导入模板/导出模板）
	private String name;//模板名称
	private String templateName;//模板编码
	private String user;//负责人
	private String versionNo;//版本号
	private String note;//说明
	private String path;//存储路径
	private String busInfo;//业务模块
	
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
	public static void main(String[] args) {
		CreateCodeUtils.autoCreateCode(Template.class, false, ActionType.JSP);
	}
}