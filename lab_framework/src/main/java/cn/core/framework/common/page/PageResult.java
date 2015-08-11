package cn.core.framework.common.page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class PageResult implements Serializable {
	private static final long serialVersionUID = -4500981451969706619L;
	private int pageSize = 15;
	private String action;
	private String order;
	private String orderBy;
	private String queryColumn;
	private String queryValue;
	private PageBean pageBean = new PageBean(1,pageSize);
	@SuppressWarnings("rawtypes")
	private List<?> resultList = new ArrayList();
	private List<OrderCondition> orderList = new ArrayList<OrderCondition>();
	private List<QueryCondition> queryList = new ArrayList<QueryCondition>();
	public String getQueryColumn() {
		return queryColumn;
	}
	public void setQueryColumn(String queryColumn) {
		this.queryColumn = queryColumn;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<?> getResultList() {
		return resultList;
	}
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
	public List<OrderCondition> getOrderList() {
		return orderList;
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月20日 上午9:39:59 <br>
	 * <strong>概要 : </strong> <br>
	 * 0.0.33版本去除，建议使用addOrder方法
	 * <strong>修改记录 : </strong> <br>
	 * @param orderList
	 */
	@Deprecated
	public void setOrderList(List<OrderCondition> orderList) {
		this.orderList = orderList;
	}
	public List<QueryCondition> getQueryList() {
		return queryList;
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月20日 上午9:39:05 <br>
	 * <strong>概要 : </strong> <br>
	 * 建议使用addCondition进行条件添加
	 * <strong>修改记录 : </strong> <br>
	 * @param queryList 
	 */
	@Deprecated
	public void setQueryList(List<QueryCondition> queryList) {
		this.queryList = queryList;
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
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月20日 上午9:22:21 <br>
	 * <strong>概要 : </strong> <br>
	 * 向查询容器内，添加查询条件
	 * <strong>修改记录 : </strong> <br>
	 * @param qc 查询条件
	 */
	public void addCondition(QueryCondition qc){
		Assert.notNull(qc,"条件查询对象不允许为空");
		this.queryList.add(qc);
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月20日 上午9:22:52 <br>
	 * <strong>概要 : </strong> <br>
	 * 向查询容器内，添加查询条件
	 * <strong>修改记录 : </strong> <br>
	 * @param field 查询字段
	 * @param operator 匹配操作，相等 || 小于 || 大于 || like 等...
	 * @param value 查询条件匹配值
	 */
	public void addCondition(String field,String operator,Object value){
		Assert.hasText(field,"添加查询条件：查询字段不允许为空");
		Assert.hasText(field,"添加查询条件：查询类型不允许为空");
		Assert.notNull(value,"添加查询条件：value不允许为空");
		this.queryList.add(new QueryCondition(field,operator,value));
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月20日 上午9:23:42 <br>
	 * <strong>概要 : </strong> <br>
	 * 向查询容器内，添加查询条件
	 * <strong>修改记录 : </strong> <br>
	 * @param jpql 查询条件jpql语句
	 */
	public void addCondition(String jpql){
		Assert.hasText(jpql,"添加查询条件：jpql语句不允许为空");
		this.queryList.add(new QueryCondition(jpql));
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月20日 上午9:24:03 <br>
	 * <strong>概要 : </strong> <br>
	 * 想查询容器内，添加排序条件
	 * <strong>修改记录 : </strong> <br>
	 * @param order	排序字段
	 * @param orderBy 排序类型
	 */
	public void addOrder(String order,String orderBy){
		Assert.hasText(order, "添加排序条件：排序字段不允许为空");
		Assert.hasText(orderBy, "添加排序条件：排序类型不允许为空");
		this.orderList.add(new OrderCondition(order, orderBy));
	}

	
}
