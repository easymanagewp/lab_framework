package cn.core.framework.common.page;
public class OrderCondition {
	
	public static final String ORDER_DESC = "DESC"; 
	public static final String ORDER_ASC = "ASC"; 
	
	private String order;
	private String orderBy;
	public OrderCondition() {
	}
	
	public OrderCondition(String order,String orderBy) {
		this.order = order;
		this.orderBy = orderBy;
	}
	
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	
}