package cn.core.framework.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

public class MapUtils {
	private static Logger log = Logger.getLogger(MapUtils.class);

	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月9日 下午1:09:10 <br>
	 * <strong>概要 : </strong> <br>
	 * 将Object对象转换成Map对象，取属性名作为key
	 * @param obj 需要转换的object对象
	 * @param convertProperty 需要进行转换的属性
	 * @return 转换完成的Map对象
	 */
	public static Map<String,Object> object2Map(Object obj,String... convertProperty){
		return object2Map(obj,null, convertProperty);
	}
	
	public static Map<String,Object> object2Map(Object obj,PropertyFilter filter,String... convertProperty){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		for(String property : convertProperty){
			try {
				Object value = PropertyUtils.getProperty(obj, property);
				if(filter!=null){
					filter.filter(resultMap, property, value);
				}else{
					resultMap.put(property, value);
				}
			} catch (Exception e) {
				log.error("",e);
				if(filter!=null){
					filter.filter(resultMap, property, null);
				}else{
					resultMap.put(property, null);
				}
			}
		}
		
		return resultMap;
	}
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月9日 下午1:09:50 <br>
	 * <strong>概要 : </strong> <br>
	 * 将一组Object对象转换成一组Map对象，取属性名作为key
	 * @param objects 需要转换的对象的集合
	 * @param convertProperty 需要进行转换的属性
	 * @return 转换完成的Map对象
	 */
	public static List<Map<String,Object>> objects2MapList(Collection<?> objects,String ...convertProperty){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Object obj : objects){
			mapList.add(object2Map(obj, convertProperty));
		}
		return mapList;
	}
	
	public static List<Map<String,Object>> objects2MapList(Collection<?> objects,PropertyFilter filter,String ...convertProperty){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Object obj : objects){
			mapList.add(object2Map(obj,filter, convertProperty));
		}
		return mapList;
	}
	
}
