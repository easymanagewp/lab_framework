package cn.core.framework.spring;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

public class MultifileReloadableResourceBundleMessageResource extends ReloadableResourceBundleMessageSource {
	
	@Override
	protected Properties loadProperties(Resource resource, String filename)
			throws IOException {
		if(filename.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)){
			Properties props = new Properties();
			String propName = StringUtils.cleanPath(filename+".properties");
			propName = propName.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length());
			Enumeration<URL> urls = MultifileReloadableResourceBundleMessageResource.class.getClassLoader().getResources(propName);
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				Properties prop = new Properties();
				prop.load(url.openStream());
				for(String name : prop.stringPropertyNames()){
					if(!props.containsKey(name)){
						props.setProperty(name, prop.getProperty(name));
					}
				}
			}
			return props;
		}else{
			return super.loadProperties(resource, filename);
		}
	}

}
