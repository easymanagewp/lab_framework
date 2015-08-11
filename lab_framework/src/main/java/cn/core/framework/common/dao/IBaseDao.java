package cn.core.framework.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.core.framework.common.page.OrderCondition;
import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.exception.EntityNotFindException;

/**
 * <strong>创建信息:</strong>Roy Wang 2015年7月6日 下午4:46:04  <br>
 * <strong>概要 : </strong> <br>
 * 基础的数据交互接口，提供了增/删/改/查相关的基础接口<br>
 */
public interface IBaseDao<P>{
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月6日 下午4:48:31 <br>
	 * <strong>概要 :返回全部数据，默认排序 </strong> <br>
	 * 获得当前实体下全部数据<br>
	 * <strong>修改记录 : </strong> <br>
	 * @return list
	 */
	public List<P> find();
	
	/**
	 * <strong>Description : 返回全部子集数据，默认排序</strong> <br>
	 * @param pid 上级节点id
	 * @return List
	 */ 
	public List<P> findByPid(String pid);
	/**
	 * <strong>Description : 根据ids获取全部数据列表,返回全部子集数据，默认排序</strong> <br>
	 * @param ids ids字符串
	 * @return List<P>
	 */
	public List<P> findByIds(String... ids);
	
	/**
	 * <strong>Description : 根据ids获取全部数据列表,返回全部子集数据，默认排序</strong> <br>
	 * @param ids ids字符串
	 * @return List<P>
	 */
	public List<P> findByIds(String ids);
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月6日 下午4:48:31 <br>
	 * <strong>概要 : 返回非软删除数据，按sort升序</strong> <br>
	 * 获得当前实体下全部数据<br>
	 * <strong>修改记录 : </strong> <br>
	 * @return list
	 */
	public List<P> list();
	
	/**
	 * <strong>概要 : 返回非软删除子集数据，按sort升序</strong> <br>
	 * @param pid 上级节点id
	 * @return List
	 */ 
	public List<P> listByPid(String pid);
	
	/**
	 * <strong>Description : 根据ids获取全部数据列表,返回非软删除子集数据，按sort升序</strong> <br>
	 * @param ids ids
	 * @return List<P>
	 */
	public List<P> listByIds(String... ids);
	
	/**
	 * <strong>Description : 根据ids获取全部数据列表,返回非软删除子集数据，按sort升序</strong> <br>
	 * @param ids ids字符串
	 * @return List<P>
	 */
	public List<P> listByIds(String ids);
	
	/**
	 * <strong>Description : 根据条件获取指定数据</strong> <br>
	 * @param queryConditionsList filter集合
	 * @param orderConditionsList 排序集合
	 * @param startRow 开始行数
	 * @param pageSize 数据条数
	 * @return List<?> 
	 */
	public List<P> query(List<QueryCondition> queryConditionsList, List<OrderCondition> orderConditionsList,int startRow,int pageSize);
	/**
	 * <strong>Description :获取当前对象 </strong> <br>
	 * @param id 序列化Id
	 * @return P
	 */
	public P findById(Serializable id);
	
	/**
	 * <strong>Description : 根据pageResult获取带分页的结果集</strong> <br>
	 * @param pageResult pageResult
	 * @return PageResult
	 */
	public PageResult getPageResult(PageResult pageResult);
	/**
	 * <strong>Description : 新增一条记录</strong> <br>
	 * @param po
	 */
	public void add(P po);
	/**
	 * <strong>Description : 根据实体删除一条记录</strong> <br>
	 * @param po
	 */
	public void delete(P po);
	/**
	 * <strong>Description : 根据id删除一条记录</strong> <br>
	 * @param id
	 */
	public void delete(Serializable id);
	/**
	 * <strong>Description : 删除当前集合</strong> <br>
	 * @param coll 数据集
	 */
	public void deleteAll(Collection<P> coll);
	/**
	 * <strong>Description : 修改当前记录</strong> <br>
	 * @param po
	 */
	public void update(P po);
	/**
	 * <strong>Description : 获取父级对象的属性名称</strong> <br>
	 * @return String
	 */
	public String getParentName();
	/**
	 * <strong>Description : 得到获取根节点的语句</strong> <br>
	 * @return String
	 */
	public String getRootCondition();
	/**
	 * <strong>Description : 当前节点的深度</strong> <br>
	 * @return 
	 */
	public int getLevel(P p);
	/**
	 * <strong>Description : 获取当前对象的根节点</strong> <br>
	 * @return
	 * @throws EntityNotFindException
	 */
	public P findRoot() throws EntityNotFindException;

}
