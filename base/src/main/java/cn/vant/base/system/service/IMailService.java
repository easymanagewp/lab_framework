package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.service.IBaseService;
import cn.vant.base.system.vo.MailVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:55:16 </strong> <br>
 * <strong>概要 : 短信邮件service</strong> <br>
 */
@Transactional
public interface IMailService extends IBaseService<MailVo> {

}
