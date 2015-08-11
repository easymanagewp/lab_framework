package cn.vant.base.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidFileTypeException;
import cn.core.framework.log.Logger;
import cn.core.framework.utils.ApplicationUtils;
import cn.vant.base.system.dao.IUploadDao;
import cn.vant.base.system.po.Upload;
import cn.vant.base.system.service.IUploadService;
import cn.vant.base.system.vo.UploadVo;

@Service("sys.uploadService")
public class UploadServiceImpl extends BaseServiceImpl<UploadVo, Upload> implements
		IUploadService {
	
	@Autowired IUploadDao uploadDao;
	
	public Logger log = Logger.getLogger(this.getClass());
	final String UPLOAD_PATH = (String)ApplicationUtils.getValue("config.upload.path"); 
	final String SERVER_BASE = (String)ApplicationUtils.getValue("config.server.path"); 
	final String UPLOAD_ACCEPT =  ((String)ApplicationUtils.getValue("config.upload.accept")).toLowerCase(); 
	
	@Override
	public String add(File file, String busInfo,String busId) throws InvalidFileTypeException,GlobalException {
		if(null==file)
			return null;
		
		String dirPath = (SERVER_BASE + UPLOAD_PATH + UPLOAD_ACCEPT + busInfo).replace("\\", File.separator);
		File f = new File(dirPath);
		if(!f.exists())
			f.mkdirs();
		//文件后缀
		String extName = file.getName().substring(file.getName().lastIndexOf("."));
		
		if(UPLOAD_ACCEPT.indexOf(extName.replace(".", "").toLowerCase())>-1){
			//文件名
			String fileName = System.currentTimeMillis()+new Random().nextInt(100)+extName;
			File target = new File(dirPath +File.separator+fileName);
			file.renameTo(target); 
			String path = (UPLOAD_PATH + busInfo).replace("\\", File.separator) +File.separator+fileName;
			
			UploadVo v = new UploadVo();
			v.setBusInfo(busInfo);
			v.setPath(path.replace("\\", "/"));
			v.setSize(file.length());
			v.setType(extName);
			v.setTrueName(file.getName());
			v.setBusId(busId);
			add(v);
			return v.getPath();
		}else{
			log.warn("非法的文件类型 "+extName);
			throw new InvalidFileTypeException("非法的文件类型");
		}
	}

	@Override
	public UploadVo findByPath(String path) throws InvalidFileTypeException,
			GlobalException {
		return po2Vo(uploadDao.findByPath(path));
	}

	@Override
	public List<UploadVo> find(String busInfo,String busId) throws InvalidFileTypeException,
			GlobalException {
		List<Upload> pList = uploadDao.find(busInfo,busId);
		List<UploadVo> vList = new ArrayList<UploadVo>();
		for(Upload p:pList){
			vList.add(po2Vo(p));
		}
		return vList;
	}
	
}
