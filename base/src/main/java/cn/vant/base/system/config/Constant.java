package cn.vant.base.system.config;

public class Constant extends cn.core.framework.config.Constant{
	
	public static class User extends cn.core.framework.config.Constant.User{
		
		public static final String USER_INFO_CACHE_KEY = "account.info";
		
		public static final String FUNCTION_INFO_CACHE_KEY = "user.function.info";
		
		public static final String USER_ROLE_INFO = "account.role.info";
		
	}
	
	public static class Function {
		
		public static final String ALL_FUNCTION_CACHE_KEY = "all.function";
		
		public static final String USER_FUNCTION_CACHE_KEY = "user.function";
		
	}
	
	public static class Cache {
		
		public static final String COMMON_CACHE_KEY = "common.cache";
		
	}
	
}
