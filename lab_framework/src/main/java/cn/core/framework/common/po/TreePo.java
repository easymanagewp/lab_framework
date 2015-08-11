package cn.core.framework.common.po;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:19:00 </strong> <br>
 * <strong>概要 : </strong> <br>
 * 树形Po（Persistence Object - 持久化对象）<br>
 */
@MappedSuperclass
public abstract class TreePo<P extends TreePo<?>> extends Po<P>{
	
	//#--- 常量
	/**
	 * 父节点属性key
	 */
	public static final String PARENT_NAME = "parent";
	/**
	 * 节点名称属性key
	 */
	public static final String NAME = "name";
	/**
	 * 跟节点级别
	 */
	public static final int ROOT_LEVEL = 0;
	//---#
	
	//#--- 持久化字段
	private String name;	//节点名称
	private P parent;		//上级节点
	//---#
	
	//#--- getter/setting
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:31:40 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取父节点，如果父节点不存在，则返回null<br>
	 * <strong>修改记录 : </strong> <br>
	 *@return 父节点
	 */
	@ManyToOne
	@JoinColumn(name = "pid")
	public P getParent() {
		return parent;
	}

	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:32:14 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置父节点<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param parent 父节点
	 */
	public void setParent(P parent) {
		this.parent = parent;
	}

	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:32:58 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 获取节点名称<br>
	 * <strong>修改记录 : </strong> <br>
	 *@return 节点名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:33:18 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 设置节点名称<br>
	 * <strong>修改记录 : </strong> <br>
	 *@param name 节点名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	//---#
	
	//#--- 静态方法
	/**
	 * <strong>创建信息:Roy Wang 2015年7月6日 下午4:33:34 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * 判断指定对象是否为TreePo（Tree Persistence Object - 树形可持久化对象）
	 * <strong>修改记录 : </strong> <br>
	 * @param pClazz 需要判断的类
	 * @return 
	 * 	true - 指定类为TreePo<br>
	 * 	false - 指定类非TreePo<br>
	 */
	public static boolean isTreePo(Class<?> pClazz) {
		return TreePo.class.isAssignableFrom(pClazz);
	}
	//---#
	
}
