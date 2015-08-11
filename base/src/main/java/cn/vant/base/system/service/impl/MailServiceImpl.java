package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.utils.CollectionUtils;
import cn.core.framework.utils.message.MessageUtils;
import cn.vant.base.system.dao.IMailDao;
import cn.vant.base.system.po.Mail;
import cn.vant.base.system.service.IMailService;
import cn.vant.base.system.vo.MailVo;

@Service("sys.mailService")
public class MailServiceImpl extends BaseServiceImpl<MailVo, Mail> implements
		IMailService {
	
	@Autowired IMailDao mailDao;
	@SuppressWarnings("deprecation")
	@Override
	public PageResult pageResult(MailVo vo, PageResult pageResult)
			throws GlobalException {
		List<QueryCondition> queryList = pageResult.getQueryList();
		if(CollectionUtils.isEmpty(queryList))
			queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("type",QueryCondition.EQ,vo.getType()));
		pageResult.setQueryList(queryList);
		
		return super.pageResult(pageResult);
	}
	
	@Override
	public void add(MailVo mailVo) throws GlobalException {
		String result = "";
		boolean isMultithreading = false;
		if("SMS".equalsIgnoreCase(mailVo.getType())){
			result = MessageUtils.sendMail(mailVo.getReceiver(), mailVo.getSubject(), mailVo.getContent(), isMultithreading);
		}else{
			result = MessageUtils.sendSms(mailVo.getReceiver(), mailVo.getContent(), isMultithreading);
		}
		mailVo.setResult(result);
		mailDao.add(vo2Po(mailVo));
	}
	
}
