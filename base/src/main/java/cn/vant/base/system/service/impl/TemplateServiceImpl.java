package cn.vant.base.system.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.export.ExportUtils;
import cn.core.framework.utils.importx.ImportUtils;
import cn.vant.base.system.po.Template;
import cn.vant.base.system.service.ITemplateService;
import cn.vant.base.system.vo.TemplateVo;

@Service("sys.templateService")
public class TemplateServiceImpl extends BaseServiceImpl<TemplateVo,Template> implements
		ITemplateService {
	
	@Override
	public void add(TemplateVo v) throws GlobalException {
		upload(v);
		super.add(v);
	}
	
	@Override
	public void update(TemplateVo v) throws GlobalException, InvalidIdException, EntityNotFindException {
		upload(v);
		super.update(v);
	}
	
	private void upload(TemplateVo v){
		if(null==v.getFile()) return;
		
		String path = null;
		if(Template.EXPORT.equalsIgnoreCase(v.getType())){
			path = ExportUtils.uploadTemplate(v.getFile(), v.getTemplateName());
			if(null!=path)
				path = path.replace(ExportUtils.SERVER_BASE, "").replace(File.separator,"/");
		}else if(Template.IMPORT.equalsIgnoreCase(v.getType())){
			path = ImportUtils.uploadTemplate(v.getFile(), v.getTemplateName());
			if(null!=path)
				path = path.replace(ImportUtils.SERVER_BASE, "").replace(File.separator,"/");
		}
		
		v.setPath(path);
	}
	
	@Override
	public void data2Vo(String[] values, TemplateVo v,String param) {
		v.setType(values[1]);	//模板类别
		v.setBusInfo(values[2]);	//业务模块
		v.setName(values[3]);	//模板名称
		v.setTemplateName(values[4]);	//模板编码
		v.setVersionNo(values[5]);	//版本编码
		v.setPath(values[6]);	//存储路径
		v.setNote(values[7]);	//业务模块
	}
}
