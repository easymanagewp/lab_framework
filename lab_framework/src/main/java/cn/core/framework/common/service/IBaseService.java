package cn.core.framework.common.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.page.OrderCondition;
import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.exception.EntityExistedException;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;


@Transactional
public interface IBaseService <V> {
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:59:25 </strong> <br>
	 * <strong>概要 : 通用保存</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@throws EntityExistedException
	 *@throws InvalidIdException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(V v) throws EntityExistedException, InvalidIdException, GlobalException;

	/**
	 * <strong>创建信息: 2015年7月6日 下午2:59:47 </strong> <br>
	 * <strong>概要 : 通用新增</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@throws EntityExistedException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void add(V v) throws EntityExistedException,GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:00:03 </strong> <br>
	 * <strong>概要 : 通用修改</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(V v) throws InvalidIdException, EntityNotFindException,GlobalException;
	
	/**
	 * <strong>创建信息:2015年7月10日 上午10:57:04 </strong> <br>
	 * <strong>概要 : 记录copy,基层方法只处理单表，复杂业务自行重写父类方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param sourceId 被copy数据id
	 *@return copy后的数据id
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String copy(String sourceId) throws InvalidIdException, EntityNotFindException,GlobalException;
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:00:15 </strong> <br>
	 * <strong>概要 : 通用软删除</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param ids
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update2del(String... ids) throws InvalidIdException,EntityNotFindException,GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:00:32 </strong> <br>
	 * <strong>概要 : 通用删除</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param ids
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(String... ids) throws InvalidIdException,EntityNotFindException,GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:00:44 </strong> <br>
	 * <strong>概要 : 通用分页</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param pageResult
	 *@return PageResult 带有分页信息的PageResult对象
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageResult pageResult(V v,PageResult pageResult) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:00:58 </strong> <br>
	 * <strong>概要 : 获取所有未置删除数据，按sort升序</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@return List<V> 当前对象的全部数据
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> list() throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:01:27 </strong> <br>
	 * <strong>概要 : 根据条件获取数据</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return List<V> 根据条件返回数据，默认返回全部数据
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> list(V v) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:01:42 </strong> <br>
	 * <strong>概要 : 根据ids获取所有未置删除数据，按sort升序</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param ids ids字串
	 *@return List<V> ids数据列表
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> listByIds(String ids) throws GlobalException;
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:01:42 </strong> <br>
	 * <strong>概要 : 根据ids获取所有未置删除数据，按sort升序</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param id id
	 *@return List<V> ids数据列表
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> listByIds(String... id) throws GlobalException;
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:01:42 </strong> <br>
	 * <strong>概要 : 获取获取pId的子集,未置删除数据，按sort升序</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param pId
	 *@return List<V> pId的子集数据列表
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> listByPid(String pId) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:01:52 </strong> <br>
	 * <strong>概要 : 获取当前对象的根节点</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@return V 返回当前对象的跟节点
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public V findRoot() throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:02:05 </strong> <br>
	 * <strong>概要 : 获取pId的子集列表（带层级关系）</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param pid
	 *@return List<V> pId的子集列表（带层级关系）
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> treeList(String pid) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:03:58 </strong> <br>
	 * <strong>概要 : 获取pId的子集列表（带层级关系的json）</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param pid
	 *@return StringBuffer pId的所有子集数据（带层级关系的json）
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public StringBuffer tree(String pid) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:04:25 </strong> <br>
	 * <strong>概要 : 获取全部数据列表（带层级关系）</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@return List<V> 当前对象全部数据列表（带层级关系）
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> treeList() throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:04:36 </strong> <br>
	 * <strong>概要 : 根据条件(查询、排序)获取数据列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param queryConditionsList
	 *@param orderConditionsList
	 *@return List<V> 按要求进行排序、筛选得到的数据列表
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> list(List<QueryCondition> queryConditionsList, List<OrderCondition> orderConditionsList) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:04:48 </strong> <br>
	 * <strong>概要 : 根据查询条获取数据列表</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param queryConditionsList
	 *@return List<V> 按要求进行筛选得到的数据列表
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> list(List<QueryCondition> queryConditionsList) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:04:58 </strong> <br>
	 * <strong>概要 : 根据条件获取数据集合</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param queryConditionsList
	 *@param orderConditionsList
	 *@param startRow
	 *@param pageSize
	 *@return List<V> 按要求进行筛选，排序得到的指定数据列表
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> list(List<QueryCondition> queryConditionsList, List<OrderCondition> orderConditionsList,int startRow,int pageSize) throws GlobalException;
	/**
	 * <strong>创建信息: 2015年7月6日 下午3:05:10 </strong> <br>
	 * <strong>概要 : 根据id获得对象</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param id
	 *@return Vo
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public V findById(String id)throws InvalidIdException,EntityNotFindException,GlobalException;
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月6日 下午4:48:31 <br>
	 * <strong>概要 :返回全部数据，默认排序 </strong> <br>
	 * 获得当前实体下全部数据<br>
	 * <strong>修改记录 : </strong> <br>
	 * @return list
	 * @throws GlobalException 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> find() throws GlobalException;
	
	/**
	 * <strong>Description : 返回全部子集数据，默认排序</strong> <br>
	 * @param pid 上级节点id
	 * @return List
	 * @throws GlobalException 
	 */ 
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> findByPid(String pid) throws GlobalException;
	/**
	 * <strong>Description : 根据ids获取全部数据列表,返回全部数据，默认排序</strong> <br>
	 * @param id id
	 * @return List<P>
	 * @throws GlobalException 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> findByIds(String... id) throws GlobalException;
	
	/**
	 * <strong>Description : 根据ids获取全部数据列表,返回全部数据，默认排序</strong> <br>
	 * @param ids ids字符串
	 * @return List<P>
	 * @throws GlobalException 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<V> findByIds(String ids) throws GlobalException;

	/**
	 * <strong>创建信息:2015年7月10日 上午10:36:36 </strong> <br>
	 * <strong>概要 : 数据导入,默认从第四行开始</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v 操作对象 
	 *@param type 导入模式  2表示先删在加
	 *@param param 自定义参数
	 *@param dataArrays 数据对象（解析自excel）
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void importData(V v, String type,String param,String[][] dataArrays)throws GlobalException;
	

}
