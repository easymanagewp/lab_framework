package cn.vant.base.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.FavoriteVo;

@Transactional
public interface IFavoriteService extends IBaseService<FavoriteVo> {

	void updateFavorites(List<FavoriteVo> favorites,AccountVo accountVo) throws GlobalException;

	List<FavoriteVo> list(String ticket,AccountVo accountVo) throws GlobalException;

	
}
