package cn.vant.base.system.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * <strong>创建信息: 2015年7月8日 上午10:27:18 </strong> <br>
 * <strong>概要 : 后台首页action</strong> <br>
 */
@Controller("sys.homeAction")
@RequestMapping("sys/home")
public class HomeAction{
	final String VIEW_PATH = "/sys/home/";
	
	@RequestMapping("main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PATH+"main");
		return mav;
	}
	
	@RequestMapping("header.do")
	public ModelAndView header() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PATH+"header");
		return mav;
	}
	
	@RequestMapping("middle.do")
	public ModelAndView middle() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PATH+"middle");
		return mav;
	}
	
	@RequestMapping("left.do")
	public ModelAndView left() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PATH+"left");
		return mav;
	}
	
	@RequestMapping("index.do")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PATH+"index");
		return mav;
	}

	public String getViewPath() {
		return VIEW_PATH;
	}
	
}