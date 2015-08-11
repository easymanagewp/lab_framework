package cn.core.framework.common.po;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import cn.core.framework.exception.GlobalException;
import cn.core.framework.utils.BeanUtils;

/**
 * <strong>创建信息:Roy Wang 2015年7月6日 下午2:58:14 </strong> <br>
 * <strong>概要 : </strong> <br>
 * 基础的Po - Persistence Object(持久化对象)，提供了可持久化对象的基础属性
 */
@MappedSuperclass
public abstract class Po<P>{
	
	//#--- status enum
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午2:59:17 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 状态信息，用于表示两种相应状态，Yes？No<br>
	 */
	public enum Status {
		
		/**
		 * 表示状态为No<br>
		 */
		N("N"),
		/**
		 * 状态为Yes<br>
		 */
		Y("Y");
		
		private String str;
		Status(String str){
			this.str = str;
		}
		@Override
		public String toString() {
			return str;
		}
	}
	//---#
	
	//#--- 数据字段 (可持久化)
	private String id; 					// 主键
	private Integer version;			// 数据本号
	private Status isDel = Status.N;	// 是否已经被删除/冻结/不可现实
	private Long createTime; 			// 创建时间
	private Long lastUpdTime;			// 最后更新时间
	private Integer sort = 0;			// 数据排序规则
	//---#
	
	//#--- 数据字段（非持久化）
	private Class<P> pClazz;	// 继承类的类型
	//---#
	
	//#--- 初始化对象
	/**
	 * <strong>创建信息: Roy Wang 2015年7月6日 下午3:03:17 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 初始化Po对象包含以下步骤：自动获取继承者的类型<br>
	 * <strong>修改记录 : </strong> <br>
	 */
	@SuppressWarnings("unchecked")
	public Po() {
		Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.pClazz = (Class<P>) parameterizedType[0];
        }
	}
	//---#

	//#--- getter/setter
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:05:25 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取实体序号（用于排序）<br>
	 * @return 实体序号
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:06:19 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置实体序号<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param sort 实体序号 
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:06:41 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取实体ID，在添加的时候，实体Id为自动生成，生成值为一个32位的UUID<br>
	 * <strong>修改记录 : </strong> <br>
	 * @return 实体ID（32位 UUid 值）
	 */
	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:07:48 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置实体ID，一般无需手动设置，在数据添加过程中，系统会自动生成一个32位的UUID作为实体id<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param id 实体id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:08:40 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取实体状态，用于确定实体是否已经被删除<br>
	 * <strong>修改记录 : </strong> <br>
	 * @return 实体状态 <br>
	 * 	Po.Status.Y - 已经被删除<br>
	 * 	Po.status.N - 尚未被删除（默认）
	 */
	@Column(length = 2, nullable = true)
	public Status getIsDel() {
		return isDel;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:10:08 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置实体是否被删除的状态<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param isDel 实体删除状态<br>
	 *	Po.Status.Y - 已经被删除<br>
	 *	Po.Status.N - 尚未被删除(默认)
	 */
	public void setIsDel(Status isDel) {
		this.isDel = isDel;
	}

	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:11:19 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取实体创建时间（Time）<br>
	 * <strong>修改记录 : </strong> <br>
	 *@return 实体创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:12:06 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置实体创建时间（Time）<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param createTime 实体创建时间
	 */
	protected void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:29:30 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取实体最后更新时间<br>
	 * <strong>修改记录 : </strong> <br>
	 *@return 实体最后更新时间
	 */
	public Long getLastUpdTime() {
		return lastUpdTime;
	}

	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:29:49 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置实体最后更新时间，无需手动设定，系统底层将会自动设定该属性，如果某些业务需要更新该属性，可另外设置字段记录，该属性与业务无关<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param lastUpdTime 实体最后更新时间
	 */
	public void setLastUpdTime(Long lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:12:23 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取获取实体版本，实体每次更新后，版本号会增加1，以保证实体为最新状态，当更新实体的时候，如果提交实体的版本号小于或者等于数据库版本号，则会更新失败，以此防止并发性更新<br>
	 * <strong>修改记录 : </strong> <br>
	 *@return 实体版本
	 */
	@Column(length = 9)
	@Version
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:14:19 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置实体版本，实体每次更新后，版本号会增加1，以保证实体为最新状态，当更新实体的时候，如果提交实体的版本号小于或者等于数据库版本号，则会更新失败，以此防止并发性更新<br>
	 * 此属性禁止手动设定，由系统底层自动完成设定<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param version 实体版本
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	//---#
	
	//#--- 实体操作方法
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:16:04 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 将Po（Persistence Object - 持久化对象） 转换为 Vo（View Object - 视图对象）<br> 
	 * <strong>修改记录 : </strong> <br>
	 *@param vo 视图对象
	 *@return 转换后的Po对象
	 *@throws GlobalException
	 */
	public Object toPo(Object vo) throws GlobalException {
		return toPo(vo, instance(),  new String[]{}) ;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:17:06 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 将Po（Persistence Object - 持久化对象） 转换为 Vo（View Object - 视图对象）<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param vo 视图对象
	 *@param po Po数据填充对象
	 *@return 转换完成后的Po对象
	 */
	public P toPo(Object vo,P po) {
		return toPo(vo, po,  new String[]{}) ;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:24:46 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 将Vo对象转换为可使用的Po对象<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param vo 需要进行数据转换的VO对象
	 *@param po 接受数据的Po对象
	 *@param ignoreProperty 转换过程中需要忽略的属性
	 *@return 转换完成后的Po对象
	 */
	public P toPo(Object vo,P po,String[] ignoreProperty) {
		BeanUtils.copyProperties(vo, po,ignoreProperty);
		return po;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:25:59 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 将Vo对象转换为可使用的Po对象<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param vo 需要进行数据转换的Po对象
	 *@param ignoreProperty 转换过程中需要忽略的属性
	 *@return 转换完成后的Po对象
	 *@throws GlobalException
	 */
	public P toPo(Object vo,String[] ignoreProperty) throws GlobalException {
		return toPo(vo, instance(), ignoreProperty);
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:26:48 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * <strong>修改记录 : </strong> <br>
	 *@return 获取一个新的实体对象<br>
	 *@throws GlobalException
	 */
	public P instance() throws GlobalException {
		P po;
		try {
			po = (P) pClazz.newInstance();
		} catch (InstantiationException e) {
			throw new GlobalException("pClazz.newInstance出错",e);
		} catch (IllegalAccessException e) {
			throw new GlobalException("pClazz.newInstance出错",e);
		}
		return po;
	}
	//---#

	//#--- Event
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:18:46 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 实体持久化事件，当对实体进行初次持久化（Add）操作的时候，触发此事件，完成对 实体的最后一次设定<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param t 
	 *@return
	 */
	@Transient
	public void onAdd(){
		this.setIsDel(Status.N);
		this.setCreateTime(System.currentTimeMillis());
		this.setLastUpdTime(System.currentTimeMillis());
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午3:19:59 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 实体更新时间，当对实体进行更新操作的时候，触发此事件<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param t
	 *@return
	 */
	@Transient
	public void onUpdate(){
		this.setLastUpdTime(System.currentTimeMillis());
	}
	//---#

	@Override
	public int hashCode() {
		if(StringUtils.isBlank(this.getId())){
			return 0;
		}else{
			return this.getId().hashCode();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
	
}
