package cn.core.framework.common.action;

import javax.servlet.http.HttpServletRequest;

import cn.core.framework.log.Logger;

/**
 * <strong>创建信息:</strong>Roy Wang 2015年7月7日 上午9:15:36  <br>
 * <strong>概要 : </strong> <br>
 * 最基础的Action接口，用于处理统一的Action相关操作
 */
public class Action {
	public Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月7日 上午9:18:50 <br>
	 * <strong>概要 : </strong> <br>
	 * 获取系统真实路径<br>
	 * <strong>修改记录 : </strong> <br>
	 * @param request request参数
	 * @return 系统真实路径
	 */
	public String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

}
