package cn.vant.base.system.service;

import java.io.File;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidFileTypeException;
import cn.vant.base.system.vo.UploadVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:57:02 </strong> <br>
 * <strong>概要 : 附件service</strong> <br>
 */
@Transactional
public interface IUploadService extends IBaseService<UploadVo> {
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:36:41 </strong> <br>
	 * <strong>概要 : 附件上传</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param file 文件
	 *@param busInfo 业务模块
	 *@param busId 业务Id
	 *@return 文件存储路径
	 *@throws InvalidFileTypeException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String add(File file,String busInfo,String busId) throws InvalidFileTypeException,GlobalException;
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:37:14 </strong> <br>
	 * <strong>概要 : 根据路径获取文件对象</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param path 文件路径
	 *@return UploadVo
	 *@throws InvalidFileTypeException
	 *@throws GlobalException
	 */
	public UploadVo findByPath(String path) throws InvalidFileTypeException,GlobalException;
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:37:47 </strong> <br>
	 * <strong>概要 : 根据业务编码及业务Id获取文件列表 </strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param busInfo 业务编码 
	 *@param busId 业务Id
	 *@return 
	 *@throws InvalidFileTypeException
	 *@throws GlobalException
	 */
	public List<UploadVo> find(String busInfo,String busId) throws InvalidFileTypeException,GlobalException;
}
