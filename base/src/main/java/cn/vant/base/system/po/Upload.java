package cn.vant.base.system.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.annotation.Module;
@Entity(name="sys_upload")
@Table(name="sys_upload")
@Module(value="sys.upload")
public class Upload extends Po<Upload> {
	
	private String trueName;//文件真实名称
	private String path;//文件存储路径
	private long size;//文件大小
	private String type;//文件类型
	private String busInfo;//文件属性
	private String busId;//业务id
	
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}
	
}