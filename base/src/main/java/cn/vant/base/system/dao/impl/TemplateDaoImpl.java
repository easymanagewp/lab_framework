package cn.vant.base.system.dao.impl;

import org.springframework.stereotype.Repository;
import cn.vant.base.system.dao.ITemplateDao;
import cn.vant.base.system.po.Template;
import cn.core.framework.common.dao.BaseDaoImpl;

@Repository("sys.templateDao")
public class TemplateDaoImpl extends BaseDaoImpl<Template> implements ITemplateDao {
}
