package cn.core.framework.log.annotation;

public enum LoggerType {
	Query("查询/显示"),Delete("删除"),Insert("添加"),Update("更新"),Null("无");
	
	private String str;
	private LoggerType(String str){
		this.str = str;
	}
	
	@Override
	public String toString() {
		return this.str;
	}
}
