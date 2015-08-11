package org.patchca.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CpatchcaUtils {
	public static final String CAPTCHATOKEN = "captchaToken";
	private static Log log = LogFactory.getLog(CpatchcaUtils.class);
	/**
	 * 根据HttpServletRequest校验验证码,使用默认参数名称
	 * 
	 * @return 是否验证通过
	 */
	public static boolean validate(HttpServletRequest request,String captchaName) {
		try {
			if(((String) request.getSession().getAttribute(CAPTCHATOKEN))
					.equalsIgnoreCase(request.getParameter(captchaName)))
				return true;
		} catch (Exception e) {
			log.info("验证码验证失败!");
		}
		return false;
	}

}
