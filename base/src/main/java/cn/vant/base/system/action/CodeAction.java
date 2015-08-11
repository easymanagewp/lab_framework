package cn.vant.base.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.spring.RestView;
import cn.vant.base.system.service.ICodeService;
import cn.vant.base.system.vo.CodeVo;

import com.alibaba.fastjson.JSONArray;
/**
 * <strong>创建信息: 2015年7月8日 上午10:26:46 </strong> <br>
 * <strong>概要 :公共代码action </strong> <br>
 */
@Controller("sys.codeAction")
@RequestMapping("sys/code")
@Logger(busInfo="系统管理",content="公共代码管理")
public class CodeAction extends BaseAction<CodeVo>{
	
	final String VIEW_PATH = "/sys/code/code";
	@Autowired ICodeService codeService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@RequestMapping(value="codeList",produces="application/json;charset=utf-8")
	public @ResponseBody String codeList(String busInfo,String code)throws GlobalException{
		return JSONArray.toJSONString(codeService.list(busInfo, code));
	}
	
	
	@RequestMapping(value="{busInfo}/{code}",method=RequestMethod.GET)
	public View codeList(@PathVariable(value="busInfo")String busInfo,@PathVariable(value="code")String code,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		List<String> resultList = null;
		try {
			resultList = codeService.list(busInfo, code);
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(resultList,exception);
	}
	
	
}
