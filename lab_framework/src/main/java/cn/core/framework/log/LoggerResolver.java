package cn.core.framework.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日志解析器，解析日志信息内的表达式信息
 * 
 * @author 王鹏
 */
public class LoggerResolver {
	public static List<ExpressionResolver> expressionResolvers = new ArrayList<ExpressionResolver>();
	static{
		expressionResolvers.add(new ParamExpressionResolver());
	}

	/**
	 * 解析表达式
	 * 
	 * @param expression
	 *            需要解析的表达式内容
	 * @return 解析后的文本内容
	 */
	public static String resolver(String text,Map<String,Object> environment) {
		String resultText = text;	// 记录解析后的文本内容
		// 获取日志文本内的表达式列表
		List<String> exporessions = getExpressions(text);
		// 替换所有的日志表达式为文本信息
		for (String exporession : exporessions) {
			resultText = replceExporession(resultText, exporession,
					resolverExpression(exporession,environment));
		}
		return resultText;
	}

	private static String replceExporession(String text, String exporession,
			String resolverExporession) {
		return text.replace(exporession, resolverExporession);
	}

	/**
	 * 解析表达式文本内容，替换为实际内容
	 * @param expressionText 表达式文本内容
	 * @return 实际内容
	 */
	private static String resolverExpression(String expressionText,Map<String,Object> environment) {
		for (ExpressionResolver expressionResolver : expressionResolvers) {
			Expression expression = new Expression(expressionText);
			if(expressionResolver.support(expression.getType())){
				return expressionResolver.resolver(expression.getExpression(),environment);
			}
		}
		return null;
	}

	/**
	 * 从文本信息内获取表达式信息
	 * 
	 * @param text
	 *            需要解析的文本
	 * @return
	 */
	private static List<String> getExpressions(String text) {
		List<String> expressions = new ArrayList<String>();
		String regEx = "\\%{1,1}\\{([^\\}])*\\}{1,1}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			for (int i = 0; i < matcher.groupCount(); i++) {
				expressions.add(matcher.group(i));
			}
		}
		return expressions;
	}

	/**
	 * 注册表达式解析器
	 * 
	 * @param expressionResolver
	 *            表达式解析器
	 */
	public static void registionExpressionResolver(
			ExpressionResolver expressionResolver) {
		expressionResolvers.add(expressionResolver);
	}
	
}
