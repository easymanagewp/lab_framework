package cn.core.framework.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.service.IBaseService;
import cn.core.framework.common.vo.Vo;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.spring.RestView;

public class RestAction<V extends Vo<V>> extends Action{
	@Autowired private IBaseService<V> baseService; 
	
	public IBaseService<V> getBaseService(){
		return baseService;
	}

	/**
	 * <strong>创建信息: </strong>Roy Wang 2015年7月7日 上午9:47:13 <br>
	 * <strong>概要 : </strong> <br>
	 * List
	 * <strong>修改记录 : </strong> <br>
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public View list(V v,HttpServletRequest request,HttpServletResponse response) {
		List<V> resultList = null;
		Exception exception = null;
		try {
			resultList = getBaseService().list(v);
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(resultList,exception);
	}
	
	// paging
	@RequestMapping(method=RequestMethod.GET,params={"paging=true"})
	public View page(V v,PageResult pageResult,HttpServletRequest request,HttpServletResponse response){
		PageResult result = null;
		Exception exception = null;
		try {
			result = getBaseService().pageResult(v, pageResult);
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(result,exception);
	}
	
	// findById
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public View findById(@PathVariable(value="id")String id,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		V result = null;
		try {
			result = getBaseService().findById(id);
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(result,exception);
	}
	
	// add
	@RequestMapping(method=RequestMethod.POST)
	@Logger(type=LoggerType.Insert,function="新增数据")
	public View add(V v,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		try {
			getBaseService().add(v);
		} catch (Exception e) {
			exception = e;
		}
		return new RestView(v,exception);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@Logger(type=LoggerType.Update,function="更新数据")
	public View update(V v,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		try {
			getBaseService().update(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RestView(v,exception);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,params={"ids"})
	@Logger(type=LoggerType.Delete,function="批量删除")
	public View delete4Batch(@RequestParam("ids")String ids,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		try {
			getBaseService().delete(ids);
		} catch (Exception e) {
			exception  = e;
		}
		return new RestView(null,exception);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@Logger(type=LoggerType.Delete,function="删除数据")
	public View delete(@PathVariable("id")String id) {
		Exception exception = null;
		try {
			getBaseService().delete(id);
		} catch (Exception e){
			exception = e;
		}
		return new RestView(null,exception);
	}
	
	@RequestMapping(value="transform",method=RequestMethod.DELETE,params={"isDel","ids"})
	@Logger(type=LoggerType.Delete,function="批量删除")
	public View delete4BatchUpdate(@RequestParam("ids")String ids,@RequestParam("isDel")String isDel,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		try{
			getBaseService().update2del(ids);
		} catch (Exception e){
			exception = e;
		}
		return new RestView(null,exception);
	}
	
	@RequestMapping(value="transform/{id}",method=RequestMethod.DELETE,params={"isDel"})
	@Logger(type=LoggerType.Delete,function="删除数据")
	public View delete4Update(@PathVariable("id")String id,@RequestParam("isDel")String isDel,HttpServletRequest request,HttpServletResponse response){
		Exception exception = null;
		try{
			getBaseService().update2del(id);
		} catch (Exception e){
			exception = e;
		}
		return new RestView(null,exception);
	}
	
}
