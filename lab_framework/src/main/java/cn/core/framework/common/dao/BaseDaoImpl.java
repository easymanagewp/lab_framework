package cn.core.framework.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import cn.core.framework.common.page.OrderCondition;
import cn.core.framework.common.page.PageBean;
import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.po.Po;
import cn.core.framework.common.po.Po.Status;
import cn.core.framework.common.po.TreePo;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.log.Logger;
import cn.core.framework.utils.CollectionUtils;
import cn.core.framework.utils.ObjectUtils;
import cn.core.framework.utils.StrUtils;

/**
 * <strong>创建信息:Roy Wang 2015年7月6日 下午2:57:55 </strong> <br>
 * <strong>概要 : </strong> <br>
 * 
 */
public class BaseDaoImpl<P extends Po<P>> implements IBaseDao<P> {

	@PersistenceContext
	private EntityManager entityManager;
	private Class<P> entityClazz;
	public Logger log = Logger.getLogger(this.getClass());
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public Class<P> getEntityClazz() {
		return this.entityClazz;
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		@SuppressWarnings("rawtypes")
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClazz = (Class<P>) parameterizedType[0];
		}
	}

	@Override
	public P findById(Serializable id) {
		return entityManager.find(entityClazz, id);
	}
	

	@Override
	public void add(P po) {
		po.onAdd();
		entityManager.persist(po);
	}

	@Override
	public void delete(P po) {
		entityManager.remove(po);
	}

	@Override
	public void delete(Serializable id) {
		entityManager.remove(findById(id));
	}

	@Override
	public void deleteAll(Collection<P> coll) {
		for (P t : coll)
			delete(t);
	}

	protected void deleteAll(String jpql) {
		deleteAll(find(jpql));
	}

	@SuppressWarnings("unchecked")
	protected List<P> find(String jpql) {
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void update(P po) {
		po.onUpdate();
		entityManager.merge(po);
	}
	
	protected String getEntityName(Class<?> entityCls){
		String entityName = entityCls.getSimpleName();
		Entity entityAnno = entityCls.getAnnotation(Entity.class);
		if(ObjectUtils.isNotNull(entityAnno)){
			String entityName4Anno = entityAnno.name();
			if(StringUtils.isNotBlank(entityName4Anno)){
				entityName = entityName4Anno;
			}
		}
		return entityName;
	}
	
	public List<P> find() {
		StringBuffer jpql = new StringBuffer("FROM ");
		jpql.append(getEntityName(entityClazz));
		return find(jpql.toString());
	}

	@Override
	public PageResult getPageResult(PageResult pageResult) {
		
		List<OrderCondition> orderList = pageResult.getOrderList();
		if(CollectionUtils.isEmpty(orderList)&&!StrUtils.isEmpty(pageResult.getOrder())){
			orderList = new ArrayList<OrderCondition>();
			orderList.add(new OrderCondition(pageResult.getOrder(),pageResult.getOrderBy()));
		}
		
		List<QueryCondition> queryList = pageResult.getQueryList();
		if(!StrUtils.isEmpty(pageResult.getQueryValue())){
			if(CollectionUtils.isEmpty(queryList))
				queryList = new ArrayList<QueryCondition>();
			queryList.add(new QueryCondition(pageResult.getQueryColumn(),QueryCondition.LK,pageResult.getQueryValue()));
		}
		
		Query query = getQuery(queryList,orderList);
		long totalRows = getCount(pageResult.getQueryList());

		PageBean tempPageBean = pageResult.getPageBean();
		PageBean pager = new PageBean((int) totalRows, tempPageBean.getPageSize());
		pager.refresh(tempPageBean.getCurrentPage());
		
		query.setFirstResult(pager.getStartRow());
		query.setMaxResults(pager.getPageSize());
		
		pageResult.setResultList(query.getResultList());
		pageResult.setPageBean(pager);
		return pageResult;
	}
	
	private Query getQuery(List<QueryCondition> queryConditionsList,
			List<OrderCondition> orderConditionsList) {
		StringBuffer jpql = new StringBuffer("FROM "
				+ getEntityName(entityClazz) + " WHERE isDel = '" + Po.Status.N+"' ");
		Map<String, Object> map = null;
		if (CollectionUtils.isNotEmpty(queryConditionsList)) {
			map = new LinkedHashMap<String, Object>();
			for (QueryCondition condition : queryConditionsList) {
				query(jpql, map, condition);
			}
		}

		if (CollectionUtils.isNotEmpty(orderConditionsList)) {
			jpql.append(" ORDER BY");
			for (int i = 0, k = orderConditionsList.size(); i < k; i++) {
				jpql.append(" ");
				jpql.append(orderConditionsList.get(i).getOrderBy());
				jpql.append(" ");
				jpql.append(orderConditionsList.get(i).getOrder());

				if (i != k - 1)
					jpql.append(",");
			}
		}else{
			jpql.append(" ORDER BY sort ASC");
		}
		
		Query query = entityManager.createQuery(jpql.toString());
		if (null != map) {
			for (String str : map.keySet()) {
				query.setParameter(str, map.get(str));
			}
		}
		return query;
	}

	private void query(StringBuffer jpql, Map<String, Object> map,
			QueryCondition condition) {
		jpql.append(" AND ");
		if (QueryCondition.CUSTOM.equals(condition.getOperator())) {
			jpql.append(" (");
			jpql.append(condition.getCustomJPQL());
			jpql.append(") ");
		}else if (QueryCondition.LK.equals(condition.getOperator())) {
			jpql.append(" ( "+condition.getField() );
			jpql.append(" like ");
			jpql.append("'%");
			jpql.append(condition.getValue());
			jpql.append("%')");
		}  else {
			jpql.append(condition.getField());
			jpql.append(" ");
			jpql.append(condition.getOperator());
			jpql.append(" :");
			jpql.append(condition.getField().replace(".", "_"));

			map.put(condition.getField().replace(".", "_"),condition.getValue());
		}
	}
	
	protected long getCount(List<QueryCondition> queryConditionsList) {
		StringBuffer jpql = new StringBuffer("SELECT COUNT(id) FROM "
				+ getEntityName(entityClazz)+ " WHERE 1=1 ");
		Map<String, Object> map = null;
		if (CollectionUtils.isNotEmpty(queryConditionsList)) {
			map = new LinkedHashMap<String, Object>();
			for (QueryCondition condition : queryConditionsList) {
				query(jpql, map, condition);
			}
		}

		Query query = entityManager.createQuery(jpql.toString());
		if (null != map) {
			for (String str : map.keySet()) {
				query.setParameter(str, map.get(str));
			}
		}
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<P> query(List<QueryCondition> queryConditionsList,
			List<OrderCondition> orderConditionsList,int startRow,int pageSize) {
		Query query = getQuery(queryConditionsList, orderConditionsList);
		if(startRow>-1){
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
		}
		return query.getResultList();
	}

	@Override
	public String getParentName() {
		return TreePo.PARENT_NAME;
	}

	@Override
	public String getRootCondition() {
		return getParentName() + " IS NULL ";
	}

	@Override
	public int getLevel(P p) {
		if(isTreePo()){
			@SuppressWarnings("rawtypes")
			TreePo tree = (TreePo)p;
			return getLevel(0,tree);
		}
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	private int getLevel(int level,TreePo treePo) {
		if(!ObjectUtils.isNull(treePo)){
			TreePo tree = (TreePo)treePo.getParent();
			return getLevel(level+1,tree);
		}
		return level;
	}

	@Override
	public P findRoot() throws EntityNotFindException {
		StringBuffer jpql = new StringBuffer("FROM ");
		jpql.append(getEntityName(getEntityClazz()));
		jpql.append(" WHERE 1=1 AND ");
		jpql.append(getRootCondition());
		return find(jpql.toString()).get(0);
	}

	@Override
	public List<P> findByPid(String pid){
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition(getParentName()+".id",QueryCondition.EQ,pid));
		return (List<P>)query(queryList, null, -1, -1);
	}
	
	public boolean isTreePo() {
		return TreePo.isTreePo(entityClazz);
	}

	public List<P> findByIds(String... ids) {
		return findByIds(toString(ids));
	}

	@Override
	public List<P> list() {
		StringBuffer jpql = new StringBuffer("FROM "
				+ getEntityName(entityClazz) + " WHERE isDel = '"+Status.N+"'");
		jpql.append(" ORDER BY sort ASC");
		return find(jpql.toString());
	}

	@Override
	public List<P> listByPid(String pid) {
		StringBuffer jpql = new StringBuffer("FROM "
				+ getEntityName(entityClazz) + " WHERE isDel = '"+Status.N+"'");
		jpql.append(" AND "+getParentName()+".id = '"+pid+"'");
		jpql.append(" ORDER BY sort ASC");
		return find(jpql.toString());
	}

	@Override
	public List<P> listByIds(String... ids) {
		return listByIds(toString(ids));
	}

	public String toString(String... ids) {
		String str="";
		for(int i=0,k=ids.length; i<k; i++){
			if(0!=i) str += ","; 
			str += ids[i]; 
		}
		return str;
	}

	@Override
	public List<P> findByIds(String ids) {
		StringBuffer jpql = new StringBuffer("FROM ");
		jpql.append(getEntityName(getEntityClazz()));
		jpql.append(" WHERE 1=1 ");
		jpql.append(" AND id IN ('"+toString(ids).replace(",", "','")+"')");
		return find(jpql.toString());
	}

	@Override
	public List<P> listByIds(String ids) {
		ids = StrUtils.replaceAll(ids, " ", "");
		
		StringBuffer jpql = new StringBuffer("FROM ");
		jpql.append(getEntityName(getEntityClazz()));
		jpql.append(" WHERE isDel= "+Status.N);
		jpql.append(" AND id IN ('"+toString(ids).replace(",", "','")+"')");
		return find(jpql.toString());
	}
}