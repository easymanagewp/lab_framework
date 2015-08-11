package cn.core.framework.utils;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class SystemInstance {

	private static SystemInstance instance = null;

	private ApplicationContext appContext;

	private static boolean isMutiTenant;

	public SystemInstance() {
	}

	public static boolean isMutiTenant() {
		return isMutiTenant;
	}

	public static void setMutiTenant(boolean isMutiTenant) {
		SystemInstance.isMutiTenant = isMutiTenant;
	}

	public static SystemInstance getInstance() {
		if (instance == null) {
			instance = new SystemInstance();
		}
		return instance;
	}

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public Object getBean(String beanName) {
		return getAppContext().getBean(beanName);
	}

	/**
	 * 
	 * @Title:
	 * @param
	 * @param clazz
	 *            要获取的Bean对象的类实例
	 * @param
	 * @return
	 * @return 返回类型
	 * @throws
	 */
	
	public Object getBean(Class<?> clazz) {
		return getAppContext().getBean(clazz);
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return appContext.getMessage(key, null, Locale.getDefault());
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @param args
	 * @return
	 */
	public String getMessage(String key, Object[] args) {
		return appContext.getMessage(key, args, Locale.getDefault());
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @param locale
	 * @return
	 */
	public String getMessage(String key, Locale locale) {
		return appContext.getMessage(key, null, locale);
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getMessage(String key, Object[] args, Locale locale) {
		return appContext.getMessage(key, args, locale);
	}
}