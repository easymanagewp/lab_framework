package cn.vant.base.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidFileTypeException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.vant.base.system.service.IUploadService;
import cn.vant.base.system.vo.UnitVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:46 </strong> <br>
 * <strong>概要 : 单位信息action</strong> <br>
 */
@Controller("sys.unitAction")
@RequestMapping("sys/unit")
@Logger(busInfo="系统管理",content="单位管理")
public class UnitAction extends BaseAction<UnitVo>{
	
	final String VIEW_PATH = "/sys/unit/unit";
	
	@Autowired IUploadService uploadService;
	
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@Override
	@Logger(type=LoggerType.Update,function="更新单位信息")
	public ModelAndView update(UnitVo vo, RedirectAttributes attr,
			HttpServletRequest request, HttpServletResponse response)
			throws GlobalException {
		String path = null;
		try {
			path = uploadService.add(vo.getFile(), UnitVo.SYS_LOGO,null);
		} catch (InvalidFileTypeException e) {
			throw new GlobalException("非法文件类型",e);
		} 
		vo.setLogo(path);
		ModelAndView mav = super.update(vo,attr, request, response);
		
		mav.addObject("id", vo.getId());
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	
	
}
