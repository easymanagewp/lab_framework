package cn.core.framework.common.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.core.framework.common.dao.IBaseDao;
import cn.core.framework.common.page.OrderCondition;
import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.po.Po;
import cn.core.framework.common.po.TreePo;
import cn.core.framework.common.vo.Vo;
import cn.core.framework.exception.EntityExistedException;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.log.Logger;
import cn.core.framework.utils.BeanUtils;
import cn.core.framework.utils.CollectionUtils;
import cn.core.framework.utils.StrUtils;

public class BaseServiceImpl<V extends Vo<V>,P extends Po<P>> implements IBaseService<V>{

	public Logger log = Logger.getLogger(this.getClass());
	final String CLEAR_ADD = "2";//数据导入时先删再加
	
	@Autowired private IBaseDao<P> baseDao;
	private Class<V> vClazz;
	private Class<P> pClazz;
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.vClazz = (Class<V>) parameterizedType[0];
            this.pClazz = (Class<P>) parameterizedType[1];
        }
	}
	
	public void setBaseDao(IBaseDao<P> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(V v) throws EntityExistedException,InvalidIdException, GlobalException {
		if(StrUtils.isEmpty(v.getId())){
			add(v);
		}else{
			try {
				update(v);
			} catch (EntityNotFindException e) {
				throw new InvalidIdException();
			}
		}
	}

	@Override
	public void update(V v) throws InvalidIdException, EntityNotFindException,GlobalException {
		if(StrUtils.isBlankOrNull(v.getId())) throw new InvalidIdException();
		P p = baseDao.findById(v.getId());
		if(null==p) throw new EntityNotFindException();
		BeanUtils.copyProperties(v, p, new String[]{"id"});
		baseDao.update(p);
	}
	
	@Override
	public String copy(String sourceId) throws InvalidIdException, EntityNotFindException, GlobalException  {
		V v = findById(sourceId);
		v.setId(null);
		
		P p = vo2Po(v);
		baseDao.add(p);
		return p.getId();
	}
	
	@Override
	public void update2del(String... ids) throws EntityNotFindException,
			EntityNotFindException, GlobalException {
		for(String id:ids) {
			P p = baseDao.findById(id);
			p.setIsDel(Po.Status.Y);
			baseDao.update(p);
		}
	}

	@Override
	public void delete(String... ids) throws GlobalException {
		for(String id:ids) baseDao.delete(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public PageResult pageResult(V v, PageResult pageResult)
			throws GlobalException {
		List<QueryCondition> queryList = pageResult.getQueryList();
		if(CollectionUtils.isEmpty(queryList))
			queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("isDel",QueryCondition.EQ,Po.Status.N));
		pageResult.setQueryList(queryList);
		return pageResult(pageResult);
	}
	
	public PageResult pageResult(PageResult pageResult)
			throws GlobalException {
		PageResult pr = baseDao.getPageResult(pageResult);
		@SuppressWarnings("unchecked")
		List<P> pList = (List<P>) pr.getResultList();
		pr.setResultList(toVoList(pList));
		return pr;
	}

	@Override
	public List<V> list() throws NullPointerException, GlobalException {
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("isDel",QueryCondition.EQ,Po.Status.N));
		List<P> pList = baseDao.query(queryList,null,-1,-1);
		return toVoList(pList);
	}
	
	@Override
	public List<V> listByIds(String... ids) throws GlobalException {
		return toVoList(baseDao.listByIds(ids));
	}

	public List<V> toVoList(List<P> pList) throws GlobalException {
		List<V> vList = null;
		if(null==pList||pList.size()==0)
			return vList;
		
		vList = new ArrayList<V>();
		for(P p:pList)
			vList.add(po2Vo(p));
		return vList;
	}
	
	@Override
	public List<V> list(V v) throws GlobalException {
		return list();
	}

	public List<V> list(List<QueryCondition> queryConditionsList, List<OrderCondition> orderConditionsList) throws GlobalException {
		return list(queryConditionsList, orderConditionsList, -1, -1);
	}
	
	public List<V> list(List<QueryCondition> queryConditionsList) throws GlobalException {
		return list(queryConditionsList, null, -1, -1);
	}
	
	public List<V> list(List<QueryCondition> queryConditionsList, List<OrderCondition> orderConditionsList,int startRow,int pageSize) throws GlobalException {
		List<P> pList = (List<P>) baseDao.query(queryConditionsList,orderConditionsList,startRow,pageSize);
		return toVoList(pList);
	}
	
	@Override
	public V findById(String id) throws InvalidIdException,
			EntityNotFindException,GlobalException{
		if(StrUtils.isBlankOrNull(id)) throw new InvalidIdException();
		P p = baseDao.findById(id);
		if(null==p) throw new EntityNotFindException();
		return po2Vo(p);
	}
	
	public P vo2Po(V v) throws GlobalException{
		P po;
		try {
			po = (P) pClazz.newInstance();
		} catch (InstantiationException e) {
			throw new GlobalException("pClazz.newInstance出错",e);
		} catch (IllegalAccessException e) {
			throw new GlobalException("pClazz.newInstance出错",e);
		}
		return (P) po.toPo(v, new String[]{"id","createTime","isDel","version"});
	}
	
	public V po2Vo(P p) throws GlobalException{
		V vo;
		try {
			vo = (V) vClazz.newInstance();
		} catch (InstantiationException e) {
			throw new GlobalException("vClazz.newInstance出错",e);
		} catch (IllegalAccessException e) {
			throw new GlobalException("vClazz.newInstance出错",e);
		}
		vo = vo.toVo(p);
		if (isTreePo()) {
			vo.setLevel(baseDao.getLevel(p));
		}
		return vo;
	}
	
	public List<V> po2Vos(List<P> ps) throws GlobalException{
		List<V> vs = new ArrayList<V>();
		for(P p : ps){
			vs.add(po2Vo(p));
		}
		return vs;
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月16日 下午4:21:42 <br>
	 * <strong>概要 : </strong> <br>
	 * <strong>修改记录 : </strong> <br>
	 * @param p 将Po 转换为Vo对象
	 * @return
	 * @throws GlobalException
	 */
	public V po2Vo2(P p) throws GlobalException{
		V vo = null;
		try {
			vo = (V) vClazz.newInstance();
		} catch (InstantiationException e) {
			throw new GlobalException("vClazz.newInstance出错",e);
		} catch (IllegalAccessException e) {
			throw new GlobalException("vClazz.newInstance出错",e);
		}
		org.springframework.beans.BeanUtils.copyProperties(p, vo);
		
		return vo;
	}

	@Override
	public void add(V v) throws GlobalException {
		baseDao.add(vo2Po(v));
	}

	@Override
	public List<V> listByPid(String pId) throws GlobalException {
		return toVoList(baseDao.listByPid(pId));
	}
	
	@Override
	public V findRoot() throws GlobalException {
		V v = null;
		if(isTreePo()){
			try {
				v =  po2Vo((P) baseDao.findRoot());
			} catch (EntityNotFindException e) {
				throw new GlobalException("findRoot 出错!!" ,e) ;
			}
		}
		
		return v ;
	}
	
	@Override
	public List<V> treeList(String pid) throws GlobalException {
		return appendSubsetList(new ArrayList<V>(), pid);
	}
	
	@Override
	public StringBuffer tree(String pid)
			throws GlobalException {
		StringBuffer treeBuffer = new StringBuffer();
		treeBuffer.append("[");
		List<P> treeList =  baseDao.findByPid(pid);
		if (treeList.size() > 0) {
			for (P tempPo : treeList) {
				if(!TreePo.isTreePo(tempPo.getClass())){
					throw new GlobalException("非法的 Tree");
				}
				@SuppressWarnings("rawtypes")
				TreePo po = (TreePo) tempPo;
				List<P> tempList =   baseDao.findByPid(po.getId());
				if (null!=tempList&&tempList.size() > 0)
					treeBuffer.append("{name:'" + po.getName() + "', treeNid:'" + po.getId() + "', isParent:" + true +  ",children:" + tree(po.getId())+ "},");
				else
					treeBuffer.append("{name:'" + po.getName() + "', treeNid:'" + po.getId() + "', isParent:" + false + "},");
			}
			if (treeBuffer.length() > 0)
				treeBuffer.replace(treeBuffer.length() - 1, treeBuffer.length(), "");
		}
		treeBuffer.append("]");
		return treeBuffer;
	}
	
	@Override
	public List<V> treeList() throws GlobalException {
		List<V> list = new ArrayList<V>();
		V v = this.findRoot();
		list.add(v);
		return appendSubsetList(list, v.getId());
	}
	
	private List<V> appendSubsetList(List<V> allList,String pid) throws GlobalException {
		List<V> tempList = listByPid(pid);
					
		if(!CollectionUtils.isEmpty(tempList)){
			for(V v:tempList){
				allList.add(v);
				appendSubsetList(allList, v.getId());
			}
		}
		return allList;
	}

	/**
	 * <strong>创建信息:2015年7月10日 下午7:11:42 </strong> <br>
	 * <strong>概要 : 删除已有的全部数据</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 */
	private void update2del() {
		for(P p:baseDao.find()) {
			p.setIsDel(Po.Status.Y);
			baseDao.update(p);
		}
	}
	
	@Override
	public void importData(V v,String type, String param,String[][] dataArrays) throws GlobalException {
		//先删再加
		if(CLEAR_ADD.equals(type)) this.update2del();
		
		V vo = null;
		for (int i = 4, j = dataArrays.length; i < j; i++) {
			if (dataArrays[i].length == 0)
				continue;
				
			String[] values = dataArrays[i];
			if(null==values[0] || "".equals(values[0].trim())){
				log.info("第 "+ i +"行无效数据不做导入");
				continue;
			}
			
			vo = v.instance();
			try {
				vo.setSort(Integer.valueOf(values[0]));
			} catch (Exception e) {
				vo.setSort(i);
			}
			// 数组数据对应至对象
			data2Vo(values, vo, param);
			add(vo);
		}
	}
	/**
	 * <strong>创建信息:2015年7月10日 下午2:14:41 </strong> <br>
	 * <strong>概要 : 数据类型转至vo</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param values
	 *@param v
	 */
	public void data2Vo(String[] values,V v,String param){
		log.info("根据业务 重写此方法");
	}
	
	private boolean isTreePo() {
		return TreePo.isTreePo(pClazz);
	}

	@Override
	public List<V> find() throws GlobalException {
		return toVoList(baseDao.find());
	}

	@Override
	public List<V> findByPid(String pid) throws GlobalException  {
		return toVoList(baseDao.findByPid(pid));
	}

	@Override
	public List<V> findByIds(String... ids) throws GlobalException  {
		return toVoList(baseDao.findByIds(ids));
	}

	@Override
	public List<V> listByIds(String ids) throws GlobalException {
		return toVoList(baseDao.listByIds(ids));
	}

	@Override
	public List<V> findByIds(String ids) throws GlobalException {
		return toVoList(baseDao.findByIds(ids));
	}
	
}