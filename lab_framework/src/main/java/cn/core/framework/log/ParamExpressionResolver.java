package cn.core.framework.log;

import java.util.Map;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang3.StringUtils;

public class ParamExpressionResolver implements ExpressionResolver {
	
	@Override
	public boolean support(String expressionType) {
		return StringUtils.equalsIgnoreCase(expressionType, "param");
	}

	@Override
	public String resolver(String expression,Map<String,Object> environment) {
		Object[] args = (Object[]) environment.get(Constant.ARGS_KEY);
		JXPathContext jxPathContext = JXPathContext.newContext(args);
		jxPathContext.setLenient(true);
		expression = expression.substring(1);
		expression = expression.replace(".", "/");
		expression = "."+expression;
		Object result = jxPathContext.getValue(expression);
		return null == result?"":result.toString();
	}

}
