package cn.core.framework.log;


/**
 * 表达式实体
 * @author 王鹏
 *
 */
public class Expression {
	private String type;
	private String expression;

	public Expression(String exporession) {
		exporession = exporession.substring(2);
		exporession = exporession.substring(0,exporession.length()-1);
		String[] expressionArr = exporession.split(":");
		this.type = expressionArr[0];
		this.expression = expressionArr[1];
	}

	/**
	 * 获取表达式类型
	 * @return 表达式类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 获取表达式信息
	 * @return 表达式信息
	 */
	public String getExpression() {
		return expression;
	}
	
	
}
