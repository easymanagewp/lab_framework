package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.dao.IUserMessageDao;
import cn.vant.base.system.po.Message;
import cn.vant.base.system.po.UserMessage;
import cn.vant.base.system.service.IUserMessageService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.UserMessageVo;

@Service("sys.user.messageService")
public class UserMessageServiceImpl extends BaseServiceImpl<UserMessageVo,UserMessage> implements
		IUserMessageService {
	
	@Autowired private IUserMessageDao userMessageDao;

	/*
	 * 获取收件箱邮件列表
	 */
	@Override
	public PageResult findInBox(PageResult pageResult, AccountVo accountVo) throws GlobalException {
		return findUserMessageByTypeAndPosition(pageResult, accountVo,Message.Type.MSG,UserMessage.POSITION_READABLE);
	}

	/**
	 * 获取用户邮件列表
	 * @param pageResult 请求分页数据信息
	 * @param accountVo 账户信息
	 * @param messageType 邮件类型 <br>
	 * 			Message.Type.MSG 邮件类型<br>
	 * 			Message.Type.ADV 公告类型<br>
	 * @return 用户邮件分页数据
	 */
	@SuppressWarnings("unchecked")
	private PageResult findUserMessageByTypeAndPosition(PageResult pageResult,
			AccountVo accountVo,Message.Type messageType,String position) throws GlobalException {
		// 邮件状态为可读取（未删除）
		pageResult.addCondition("position", QueryCondition.EQ, position);
		// 邮件接收人为指定人
		pageResult.addCondition("account.id",QueryCondition.EQ,accountVo.getId());
		// 邮件类型为邮件
		pageResult.addCondition("message.type",QueryCondition.EQ,messageType);
		pageResult = userMessageDao.getPageResult(pageResult);
		
		pageResult.setResultList(super.po2Vos((List<UserMessage>)pageResult.getResultList()));
		return pageResult;
	}

	@Override
	public List<UserMessageVo> findByMessageId(String messageId) throws GlobalException {
		List<QueryCondition> qcs = new ArrayList<QueryCondition>();
		qcs.add(new QueryCondition("message.id",QueryCondition.EQ,messageId));
		List<UserMessage> userMessages = this.userMessageDao.query(qcs, null, -1, -1);
		return super.po2Vos(userMessages);
	}

	/*
	 * 获取公告列表 
	 */
	@Override
	public PageResult findGongGao(PageResult pageResult, AccountVo accountVo)
			throws GlobalException {
		return findUserMessageByTypeAndPosition(pageResult, accountVo,Message.Type.ADV,UserMessage.POSITION_READABLE);
	}

	/*
	 * 获取已删除的邮件信息
	 */
	@Override
	public PageResult findDeletedMessage(PageResult pageResult,
			AccountVo accountVo) throws GlobalException {
		return findUserMessageByTypeAndPosition(pageResult,accountVo,Message.Type.MSG,UserMessage.POSITION_DEL);
	}
}
