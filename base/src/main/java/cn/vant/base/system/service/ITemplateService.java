package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.vant.base.system.vo.TemplateVo;
import cn.core.framework.common.service.IBaseService;

@Transactional
public interface ITemplateService extends IBaseService<TemplateVo> {
	
}
