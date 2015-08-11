package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.cache.GlobalCache;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.po.Po.Status;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.config.Constant;
import cn.vant.base.system.dao.IAccountDao;
import cn.vant.base.system.dao.IFavoriteDao;
import cn.vant.base.system.dao.IFunctionDao;
import cn.vant.base.system.po.Favorite;
import cn.vant.base.system.po.Function;
import cn.vant.base.system.service.IFavoriteService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.FavoriteVo;
import cn.vant.base.system.vo.FunctionVo;

@Service("sys.favoriteService")
public class FavoriteServiceImpl extends BaseServiceImpl<FavoriteVo,Favorite> implements
		IFavoriteService {

	@Autowired private IFavoriteDao favoriteDao;
	@Autowired private IFunctionDao functionDao; 
	@Autowired private IAccountDao accountDao;
	
	@Override
	public void updateFavorites(List<FavoriteVo> favorites,AccountVo accountVo) throws GlobalException {
		List<QueryCondition> qcs = new ArrayList<QueryCondition>();
		qcs.add(new QueryCondition("account.id",QueryCondition.EQ,accountVo.getId()));
		List<Favorite> favoritePos = favoriteDao.query(qcs, null, -1, -1);
		for(FavoriteVo favorite : favorites){
			favorite.setAccountVo(accountVo);
			if(!favoritePos.contains(favorite)){	// 尚未被收藏，添加到收藏栏
				favoriteDao.add(vo2Po(favorite));
			}else{	// 已经被收藏，忽略	
				for(Favorite favorite4Update : favoritePos){
					if(favorite4Update.equals(favorite) && (!favorite4Update.getAlias().equals(favorite.getAlias()) || !favorite4Update.getSort().equals(favorite.getSort()))){
						favorite4Update.setAlias(favorite.getAlias());
						favorite4Update.setSort(favorite.getSort());
						favoriteDao.update(favorite4Update);
					}
				}
			}
			favoritePos.remove(favorite);	// 在原本的收藏列表中移除已经操作的数据
		}
		
		// 删除剩余未操作数据
		for(Favorite favorite : favoritePos){	// 删除数据
			favoriteDao.delete(favorite);
		}
		
	}
	
	@Override
	public Favorite vo2Po(FavoriteVo v) throws GlobalException {
		Favorite po = new Favorite();
		Function function = functionDao.findById(v.getFunctionVo().getId());
		po.setFunction(function);
		po.setAccount(accountDao.findById(v.getAccountVo().getId()));
		po.setAlias(v.getAlias());
		po.setSort(v.getSort());
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FavoriteVo> list(String ticket,AccountVo accountVo) throws GlobalException {
		List<QueryCondition> qcs = new ArrayList<QueryCondition>();
		qcs.add(new QueryCondition("account.id",QueryCondition.EQ,accountVo.getId()));
		List<Favorite> favoritePos = favoriteDao.query(qcs, null, -1, -1);
		
		List<FunctionVo> fVos = (List<FunctionVo>) GlobalCache.getInstance().get(ticket, Constant.User.FUNCTION_INFO_CACHE_KEY);
		for(Favorite favorite : favoritePos){
			if(!fVos.contains(favorite.getFunction())){
				favorite.setIsUsed(Status.N);
				favoriteDao.update(favorite);
			}else if(favorite.getIsUsed().equals(Status.N)){
				favorite.setIsUsed(Status.Y);
				favoriteDao.update(favorite);
			}
		}
		
		return po2Vos(favoritePos);
	}
	
	
}
