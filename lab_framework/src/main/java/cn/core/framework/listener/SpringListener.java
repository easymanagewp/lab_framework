package cn.core.framework.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.core.framework.utils.ApplicationUtils;
import cn.core.framework.utils.XMLUtils;

@Component("Initializable")
public class SpringListener extends org.springframework.web.context.ContextLoaderListener{
	
	private static Logger log = LoggerFactory.getLogger(SpringListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.debug("--------------Spring contextInitialized--------------");
		ServletContext servletContext = event.getServletContext();
	    try {
	    	servletContext.setAttribute("config",XMLUtils.xml2Map("config.xml"));
	    } catch (Exception e) {
	    	log.info("加载 config 文件异常",e);
		}
	    ApplicationUtils.setServletContext(servletContext);
	    servletContext.setAttribute("basePath", ApplicationUtils.getValue("config.server.url"));
		super.contextInitialized(event);
	}
	
	
}
