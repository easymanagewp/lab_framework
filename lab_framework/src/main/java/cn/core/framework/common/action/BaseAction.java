package cn.core.framework.common.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.service.IBaseService;
import cn.core.framework.common.vo.Vo;
import cn.core.framework.exception.EntityExistedException;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.log.annotation.Logger;
import cn.core.framework.log.annotation.LoggerType;
import cn.core.framework.utils.StrUtils;
import cn.core.framework.utils.export.ExportUtils;
import cn.core.framework.utils.importx.ImportUtils;


public abstract class BaseAction<V extends Vo<V>> extends Action {
	protected static final String STATUS = "status";
	protected static final String PAGE = "page.do";
	protected static final String PAGED = "paged.do";
	protected static final String LIST = "list.do";
	protected static final String EDIT = "edit.do";
	protected static final String SHOW = "show.do";
	protected static final String ADD = "add.do";
	protected static final String COPY = "copy.do";
	protected static final String UPDATE = "update.do";
	protected static final String UPDATE_2_DEL = "update2del.do";
	protected static final String DELETE = "delete.do";
	protected static final String SAVE = "save.do";
	protected static final String EXPORT = "export.do";
	protected static final String IMPORT = "import.do";
	protected static final String DOWNLOAD = "download.do";
	protected static final String DOWNTEMP = "downtemp.do";
	
	protected static final String LEFT = "left.do";
	protected static final String FRAME = "frame.do";
	protected static final String MIDDLE = "middle.do";
	
	protected static final String REDIRECT_2_PAGE = "redirect:"+PAGE;
	protected static final String REDIRECT_2_EDIT = "redirect:"+EDIT;
	protected static final String REDIRECT_2_SHOW = "redirect:"+SHOW;
	
	protected static final String IS_EDIT = "isEdit";
	public abstract String getViewPath();
	
	@Autowired private IBaseService<V> baseService; 
	
	public IBaseService<V> baseService(){
		return baseService;
	}
	/**
	 * <strong>创建信息:2015年7月20日 下午9:22:10 </strong> <br>
	 * <strong>概要 : left页面</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=LEFT)
	public ModelAndView left(V v) throws GlobalException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getViewPath()+"_left");
		return mav;
	}
	/**
	 * <strong>创建信息:2015年7月20日 下午9:22:10 </strong> <br>
	 * <strong>概要 : frame页面</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=FRAME)
	public ModelAndView frame(V v) throws GlobalException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getViewPath()+"_frame");
		return mav;
	}
	
	/**
	 * <strong>创建信息:2015年7月20日 下午9:22:10 </strong> <br>
	 * <strong>概要 : middle页面</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=MIDDLE)
	public ModelAndView middle(V v) throws GlobalException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getViewPath()+"_middle");
		return mav;
	}
	
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:27 </strong> <br>
	 * <strong>概要 : 通用page页获取方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param pageResult
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=PAGE)
	public ModelAndView page(V v,PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException{
		pageResult = baseService.pageResult(v, pageResult);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageResult", pageResult);
		mav.addObject("vo", v);
		mav.setViewName(getViewPath()+"_page");
		return mav;
	}
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:27 </strong> <br>
	 * <strong>概要 : 通用已办page页获取方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param pageResult
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=PAGED)
	public ModelAndView paged(V v,PageResult pageResult,HttpServletRequest request,HttpServletResponse response) throws GlobalException{
		pageResult = baseService.pageResult(v, pageResult);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageResult", pageResult);
		mav.addObject("vo", v);
		mav.setViewName(getViewPath()+"_ed_page");
		return mav;
	}
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:32 </strong> <br>
	 * <strong>概要 : 通用List页获取方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=LIST)
	public ModelAndView list(V v,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = new ModelAndView();
		try {
			List<V> list = baseService.list(v);
			mav.addObject("list", list);
		} catch (GlobalException e) {
			log.info("获取数据异常!!",e);
		}
		mav.setViewName(getViewPath()+"_list");
		return mav;
	}
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:35 </strong> <br>
	 * <strong>概要 : 通用编辑前获取方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=EDIT)
	public ModelAndView edit(V v,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = new ModelAndView();
		try {
			if(null!=v && !StrUtils.isBlankOrNull(v.getId())){
				v = baseService.findById(v.getId());
				mav.addObject(IS_EDIT, "true");
			}
		} catch (InvalidIdException e) {
			log.info("无效的ID ",e);
		} catch (EntityNotFindException e) {
			log.info("获取数据失败",e);
		} 
		mav.addObject("vo", v);
		mav.setViewName(getViewPath()+"_edit");
		return mav;
	}
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:38 </strong> <br>
	 * <strong>概要 : 通用查看方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=SHOW)
	public ModelAndView show(V v,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		ModelAndView mav = edit(v,request,response);
		mav.setViewName(getViewPath()+"_show");
		return mav;
	}
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:42 </strong> <br>
	 * <strong>概要 : 通用添加方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param attr
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=ADD)
	@Logger(type=LoggerType.Insert,function="新增数据")
	public ModelAndView add(V v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		Status status = new Status();
		status.setInfo("新增记录");
		try {
			baseService.add(v);
			status.setStatus("新增成功");
		} catch (EntityExistedException e) {
			log.info("新增失败",e);
			status.setStatus("新增失败");
			status.setMessage(e.getMessage());
		} catch (GlobalException e) {
			log.info("新增失败",e);
			status.setStatus("新增失败");
			status.setMessage(e.getMessage());
		}
		attr.addFlashAttribute(STATUS, status);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
	}
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:46 </strong> <br>
	 * <strong>概要 : 通用软删除方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param attr
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=UPDATE_2_DEL)
	@Logger(type=LoggerType.Delete,function="删除数据")
	public ModelAndView update2del(V v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response)  throws GlobalException{
		Status status = new Status();
		status.setInfo("删除记录");
		try {
			baseService.update2del(v.getIds());
			status.setStatus("删除成功");
		} catch (InvalidIdException e) {
			log.info("删除失败",e);
			status.setStatus("删除失败");
			status.setMessage("无效的ID"+ e.getMessage());
		} catch (EntityNotFindException e) {
			log.info("删除失败",e);
			status.setStatus("删除失败");
			status.setMessage("未获得有效实体"+ e.getMessage());
		} catch (GlobalException e) {
			log.info("删除失败",e);
			status.setStatus("删除失败");
			status.setMessage(e.getMessage());
		}
		attr.addFlashAttribute(STATUS, status);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
	}
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:49 </strong> <br>
	 * <strong>概要 : 通用保存方法（有则新增、无则修改）</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param attr
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=SAVE)
	@Logger(type=LoggerType.Insert,function="保存数据")
	public ModelAndView save(V v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response)  throws GlobalException{
		Status status = new Status();
		status.setInfo("保存记录");
		try {
			baseService.save(v);
			status.setStatus("保存成功");
		} catch (InvalidIdException e) {
			log.info("保存失败",e);
			status.setStatus("保存失败");
			status.setMessage("无效的ID"+ e.getMessage());
		} catch (EntityExistedException e) {
			log.info("保存失败",e);
			status.setStatus("保存失败");
			status.setMessage("未获得有效实体"+ e.getMessage());
		} catch (GlobalException e) {
			log.info("保存失败",e);
			status.setStatus("保存失败");
			status.setMessage("无效的ID"+ e.getMessage());
		}
		attr.addFlashAttribute(STATUS, status);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:53 </strong> <br>
	 * <strong>概要 : 通用修改方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param attr
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=UPDATE)
	@Logger(type=LoggerType.Update,function="更新数据")
	public ModelAndView update(V v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response)  throws GlobalException{
		Status status = new Status();
		status.setInfo("修改记录");
		try {
			baseService.update(v);
			status.setStatus("修改成功");
		} catch (InvalidIdException e) {
			log.info("修改失败",e);
			status.setStatus("修改失败");
			status.setMessage("无效的ID"+ e.getMessage());
		} catch (EntityNotFindException e) {
			log.info("修改失败",e);
			status.setStatus("修改失败");
			status.setMessage("未获得有效实体"+ e.getMessage());
		} catch (GlobalException e) {
			log.info("修改失败",e);
			status.setStatus("修改失败");
			status.setMessage("无效的ID"+ e.getMessage());
		}
		attr.addFlashAttribute(STATUS, status);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
	}
	
	/**
	 * <strong>创建信息:2015年7月10日 上午10:39:57 </strong> <br>
	 * <strong>概要 : 数据copy</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param sourceId 被copy对象
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value=COPY)
	@Logger(type=LoggerType.Insert,function="拷贝数据")
	public ModelAndView copy(@RequestParam(value = "sourceId", required = true)String sourceId,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response)  throws GlobalException{
		String targetId = null;
		Status status = new Status();
		try {
			targetId = baseService.copy(sourceId);
		}catch (InvalidIdException e) {
			log.info("copy失败",e);
			status.setStatus("copy失败");
			status.setMessage("无效的ID"+ e.getMessage());
		} catch (EntityNotFindException e) {
			log.info("copy失败",e);
			status.setStatus("copy失败");
			status.setMessage("未获得有效实体"+ e.getMessage());
		} catch (GlobalException e) {
			log.info("copy失败",e);
			status.setStatus("copy失败");
			status.setMessage("copy失败"+ e.getMessage());
		}
		
 		ModelAndView mav = new ModelAndView();
 		mav.addObject("id", targetId);
		mav.setViewName(REDIRECT_2_EDIT);
		return mav;
	}
	/**
	 * <strong>创建信息: 2015年7月6日 下午2:54:55 </strong> <br>
	 * <strong>概要 : 通用删除方法</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param v
	 *@param attr
	 *@return
	 *@throws GlobalException
	 */
 	@RequestMapping(value=DELETE)
 	@Logger(type=LoggerType.Delete,function="删除数据")
	public ModelAndView delete(V v,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response)  throws GlobalException{
 		Status status = new Status();
		status.setInfo("删除记录");
		try {
			baseService.delete(v.getIds());
			status.setStatus("删除成功");
		} catch (InvalidIdException e) {
			log.info("删除失败",e);
			status.setStatus("删除失败");
			status.setMessage("无效的ID"+ e.getMessage());
		} catch (EntityNotFindException e) {
			log.info("删除失败",e);
			status.setStatus("删除失败");
			status.setMessage("未获得有效实体"+ e.getMessage());
		} 
		attr.addFlashAttribute(STATUS, status);
 		ModelAndView mav = new ModelAndView();
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
 	}
 	
 	/**
 	 * <strong>创建信息:2015年7月9日 下午6:36:35 </strong> <br>
 	 * <strong>概要 : 通用导出方法</strong> <br>
 	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
 	 *@param v vo
 	 *@param source 模板code  
 	 *@param target 导出文件名
 	 *@param request
 	 *@param response
 	 *@return
 	 *@throws Exception
 	 */
 	@RequestMapping(value = EXPORT)
 	@Logger(type=LoggerType.Query,function="导出数据")
	public String export(V v,String source,String target,HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		String path = ExportUtils.export(source, target, getExportData(v));
		try {
			down(path, target, request,response);
		} catch (IOException e) {
			log.error("导出文件出错 "+source,e);
			throw new GlobalException("导出文件出错 "+source,e);
		}
		return null;
	}
 	
 	/**
 	 * <strong>创建信息:2015年7月9日 下午6:37:58 </strong> <br>
 	 * <strong>概要 : 获取导出数据,默认调用 xxService.list(v)方法,(map.put("list", baseService.list(v)))</strong> <br>
 	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
 	 *@param v 获取导出数据
 	 *@return 带出数据map
 	 */
 	protected Map<String, Object> getExportData(V v) throws GlobalException{
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("list", baseService.list(v));
 		return map;
 	}
 	
 	/**
 	 * <strong>创建信息:2015年7月9日 下午6:00:26 </strong> <br>
 	 * <strong>概要 : 文件下载</strong> <br>
 	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
 	 *@param filePath 文件下载相对路径
 	 *@param trueName 文件下载后保存文件名
 	 *@param request
 	 *@param response
 	 *@return
 	 *@throws Exception
 	 */
 	@RequestMapping(value = DOWNLOAD)
	public String download(
			@RequestParam(value = "filePath", required = true) String filePath,
			@RequestParam(value = "trueName", required = true) String trueName,HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		//String filePath = "static/upload/sys-org/1434021281827.doc";
		String ctxPath = getRealPath(request);
		String downLoadPath = ctxPath + filePath.replace("/", File.separator);
		try {
			down(downLoadPath, trueName,request,response);
		} catch (IOException e) {
			log.error("下载文件出错 "+filePath,e);
			throw new GlobalException("下载文件出错  "+filePath,e);
		}
		return null;
	}
 	/**
 	 * <strong>创建信息:2015年7月10日 下午2:33:12 </strong> <br>
 	 * <strong>概要 : 导入模板下载方法</strong> <br>
 	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
 	 *@param templateName 模板名称
 	 *@param trueName 下载后文件名称
 	 *@param request
 	 *@param response
 	 *@return
 	 *@throws GlobalException
 	 */
 	@RequestMapping(value = DOWNTEMP)
 	public String downTemp(
 			@RequestParam(value = "templateName", required = true)String templateName,
 			@RequestParam(value = "trueName", required = true)String trueName,HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		String downLoadPath = ImportUtils.getTemplatePath(templateName);
		try {
			down(downLoadPath, trueName,request,response);
		} catch (IOException e) {
			log.error("下载文件出错 "+downLoadPath,e);
			throw new GlobalException("下载文件出错  "+downLoadPath,e);
		}
		return null;
	}

	/**
	 * <strong>创建信息: 2015年7月10日 上午10:30:04 </strong> <br>
	 * <strong>概要 : 数据导入</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
 	 *@param v vo 对象
 	 *@param file 数据对象
 	 *@param attr
 	 *@param request
 	 *@param response
 	 *@return
 	 *@throws GlobalException
 	 */
	@RequestMapping(value = IMPORT,method = RequestMethod.POST)
	@Logger(type=LoggerType.Query,function="导入数据")
	public ModelAndView importExcel(V v,@RequestParam(value = "param") String param,@RequestParam(value = "type", required = false) String type,@RequestParam(value = "file", required = false) MultipartFile file,RedirectAttributes attr,HttpServletRequest request,HttpServletResponse response) throws GlobalException {
		Status status = new Status();
		status.setInfo("数据导入");
		try {
			baseService.importData(v, type,param,ImportUtils.importData(file));
			status.setStatus("数据导入成功");
		} catch (GlobalException e) {
			status.setStatus("数据导入失败");
			e.printStackTrace();
			log.error("数据导入失败 ",e);
		}
		
		attr.addFlashAttribute(STATUS, status);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(REDIRECT_2_PAGE);
		return mav;
	}
	/**
	 * <strong>创建信息:2015年7月10日 上午10:29:42 </strong> <br>
	 * <strong>概要 : 数据导入</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@return
	 *@throws GlobalException
	 */
	@RequestMapping(value = IMPORT,method = RequestMethod.GET)
	public ModelAndView preImport() throws GlobalException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getViewPath()+"_import");
		return mav;
	}
	
	/**
	 * <strong>创建信息:2015年7月10日 下午2:16:31 </strong> <br>
	 * <strong>概要 : 文件下载</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param downLoadPath 文件绝对路径 
	 *@param trueName 文件名称
	 *@param request
	 *@param response
	 *@throws IOException
	 */
	protected void down(String downLoadPath,String trueName,HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(trueName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			log.info("文件下载出错"+downLoadPath, e);
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

 		
}