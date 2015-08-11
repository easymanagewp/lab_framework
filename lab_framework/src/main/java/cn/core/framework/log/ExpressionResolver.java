package cn.core.framework.log;

import java.util.Map;

public interface ExpressionResolver {
	
	boolean support(String expressionType);
	
	String resolver(String expression,Map<String,Object> environment);
}
