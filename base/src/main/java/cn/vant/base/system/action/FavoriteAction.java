package cn.vant.base.system.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.common.action.BaseAction;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.service.IFavoriteService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.FavoriteVo;

@Controller("sys.favoriteAction")
@RequestMapping("/sys/favorite")
@Logger(busInfo="系统管理",content="快捷菜单管理")
public class FavoriteAction extends BaseAction<FavoriteVo> {
	final String VIEW_PATH = "/sys/favorite/favorite";
		
	
	@Autowired private IFavoriteService favoriteService;
	
	@Override
	public String getViewPath() {
		return VIEW_PATH;
	}
	
	@RequestMapping(value="setting")
	public String setting(){
		return this.VIEW_PATH+"_setting";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public View list(HttpServletRequest request,@CookieValue(Constant.User.TOCKET_COOKIE_KEY)String ticket){
		Object result = null;
		Exception exception = null;
		try{
			AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
			List<FavoriteVo> favorites = favoriteService.list(ticket,accountVo);
			result = favorites;
		}catch(Exception e){
			exception = e;
		}
		return new RestView(result,exception);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Logger(type=LoggerType.Insert,function="添加快捷菜单")
	public View addFavorites(
			FavoriteVoList favorites,
			HttpServletRequest request
			){
		Exception exception = null;
		try{
			AccountVo accountVo = (AccountVo) GlobalCache.getInstance().get(request, Constant.User.USER_INFO_CACHE_KEY);
			favoriteService.updateFavorites(favorites.getFavorites(),accountVo);
		} catch(Exception e){
			exception = e;
		}
		// 结果为null，交给视图进行处理，如果exception也为null，则处理成功
		return new RestView(null,exception);
	}
	
	public static class FavoriteVoList {
		List<FavoriteVo> favorites = new ArrayList<FavoriteVo>();

		public List<FavoriteVo> getFavorites() {
			return favorites;
		}

		public void setFavorites(List<FavoriteVo> favorites) {
			this.favorites = favorites;
		}
		
	}
}