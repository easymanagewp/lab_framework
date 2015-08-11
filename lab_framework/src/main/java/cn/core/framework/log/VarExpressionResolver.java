package cn.core.framework.log;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.utils.WebContext;

public class VarExpressionResolver implements ExpressionResolver {

	@Override
	public boolean support(String expressionType) {
		return StringUtils.equalsIgnoreCase("var", expressionType);
	}

	@Override
	public String resolver(String expression, Map<String, Object> environment) {
		Object o = GlobalCache.getInstance().get(WebContext.getRequest(), expression);
		return null == o ? null : o.toString();
	}

}
