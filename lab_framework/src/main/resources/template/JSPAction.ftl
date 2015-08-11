package ${controllerPackageName};

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ${voPackageName}.${simpleEntityName}Vo;
import cn.core.framework.common.action.BaseAction;

@Controller("${module}Action")
@RequestMapping("${httpRequestMapping}")
public class ${simpleEntityName}Action extends BaseAction<${simpleEntityName}Vo> {
	final String VIEW_PATH = "${httpRequestMapping}/${entityLoverCaseName}";
		
	@Override
	public String getViewPath() {
		return VIEW_PATH;
	}
}