package cn.core.framework.common.vo;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import cn.core.framework.exception.GlobalException;
import cn.core.framework.utils.DateUtils;
import cn.core.framework.utils.PropertyConvert;

public class Vo<V> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String[] ids;
	private String pid;
	private String uuid;
	private String isDel;
	private long createTime; //创建时间
	private String createTimeStr; //创建时间
	private String updateTime; //创建人
	private String tenantId;
	private Integer sort = 0;
	private int count = 0;
	private String startDate;
	private String endDate;
	private String isCommit;
	private List<?> list;
	private int level;
	private Class<V> vClazz;
	
	@SuppressWarnings("unchecked")
	public Vo() {
		Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.vClazz = (Class<V>) parameterizedType[0];
        }
	}
	
	public V instance() throws GlobalException {
		V vo;
		try {
			vo = (V) vClazz.newInstance();
		} catch (InstantiationException e) {
			throw new GlobalException("vClazz.newInstance出错",e);
		} catch (IllegalAccessException e) {
			throw new GlobalException("vClazz.newInstance出错",e);
		}
		return vo;
	} 
	/**
	 * @throws GlobalException 
	 * @Title:  source 转换到当前 vo 
	 * @param source 
	 * @return 返回类型 
	 * @throws
	 */
	public V toVo(Object source) throws GlobalException {
		if(null==source) return null;
		return toVo(source, instance(), new String[]{});
	}
	/**
	 * @Title:  source 转换到 source 当前 vo 
	 * @param source
	 * @param target
	 * @return 返回类型 
	 * @throws
	 */
	public V toVo(Object source,V target) {
		return toVo(source, target, new String[]{});
	}
	/**
	 * @Title:  对象转化
	 * @param po
	 * @param vo
	 * @param ignoreProperty 
	 * @return 返回类型 
	 * @throws
	 */
	public V toVo(Object po,V vo,String[] ignoreProperty) {
		return toVo(po, vo, null, ignoreProperty);
	}
	
	public V toVo(Object po,V vo,PropertyConvert pc,String[] ignoreProperty){
		cn.core.framework.utils.BeanUtils.copyProperties(po, vo,pc,ignoreProperty);
		return vo;
	}
	
	public final String[] getIds() {
		return ids;
	}
	public final void setIds(String[] ids) {
		this.ids = ids;
	}
	public final String getUuid() {
		if(uuid==null)uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	public final void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public final String getIsDel() {
		return isDel;
	}
	public final void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public final long getCreateTime() {
		return createTime;
	}
	public final void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getUpdateTime() {
		return updateTime;
	}
	public final void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public final String getTenantId() {
		return tenantId;
	}
	public final void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public final Integer getSort() {
		return sort;
	}
	public final void setSort(Integer sort) {
		this.sort = sort;
	}
	public final String getStartDate() {
		return startDate;
	}
	public final void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public final String getEndDate() {
		return endDate;
	}
	public final void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public final List<?> getList() {
		return list;
	}
	public final void setList(List<?> list) {
		this.list = list;
	}
	public final int getCount() {
		return count;
	}
	public final void setCount(int count) {
		this.count = count;
	}
	public final String getIsCommit() {
		return isCommit;
	}
	public final void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCreateTimeStr() {
		createTimeStr = DateUtils.parseToDateStr(createTime);
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	@Override
	public int hashCode() {
		return this.getId()==null ? super.hashCode() : this.getId().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
	
}

