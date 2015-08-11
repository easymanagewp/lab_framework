package cn.vant.base.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.BeanUtils;
import cn.vant.base.system.dao.IAccountDao;
import cn.vant.base.system.dao.IMessageDao;
import cn.vant.base.system.dao.IUserMessageDao;
import cn.vant.base.system.po.Account;
import cn.vant.base.system.po.Message;
import cn.vant.base.system.po.UserMessage;
import cn.vant.base.system.service.IMessageService;
import cn.vant.base.system.service.IUserMessageService;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.MessageVo;
import cn.vant.base.system.vo.UserMessageVo;

@Service("sys.messageService")
public class MessageServiceImpl extends BaseServiceImpl<MessageVo, Message> implements
		IMessageService {
	
	@Autowired IMessageDao messageDao;
	@Autowired IUserMessageService userMessageService;
	@Autowired IUserMessageDao userMessageDao;
//	@Autowired IUserDao userDao;
	@Autowired IAccountDao accountDao;
	final String userId = "402881fb4d520407014d52048b860000";
	final String curUserId="402881fb4d520407014d52048b860000";
	
	@Override
	public MessageVo po2Vo(Message p) throws GlobalException {
		MessageVo v = super.po2Vo(p);
		List<UserMessageVo> vos = userMessageService.findByMessageId(p.getId());
		v.setTargetUserVoList(vos);
		List<AccountVo> accounts = new ArrayList<AccountVo>();
		for(Account account : p.getTargetAccounts()){
			AccountVo accountVo = new AccountVo();
			BeanUtils.copyProperties(account, accountVo);
			accounts.add(accountVo);
		}
		v.setTargetAccountVos(accounts);
		return v;
	}
	
	@Override
	public PageResult pageResult(MessageVo v, PageResult pageResult)
			throws GlobalException {
//		List<QueryCondition> queryList = pageResult.getQueryList();
//		if(CollectionUtils.isEmpty(queryList))
//			queryList = new ArrayList<QueryCondition>();
//		
//		List<OrderCondition> orderList = pageResult.getOrderList();
//		if(CollectionUtils.isEmpty(orderList)&&null==pageResult.getOrderBy()){
//			orderList = new ArrayList<OrderCondition>();
//			orderList.add(new OrderCondition(OrderCondition.ORDER_ASC, "readFlag"));
//		}
//			
//		String delFlag = "1";
//		switch (Integer.valueOf(v.getPosition())) {
//		case 0://已删除
//			queryList.add(new QueryCondition("receiver.id = '"+curUserId+"' OR sender.id = '"+curUserId+"'"));
//			queryList.add(new QueryCondition("sendFreeze = '"+delFlag+"' OR receiveFreeze = '"+delFlag+"'"));
//			break;
//		case 1://草稿箱
//			queryList.add(new QueryCondition("position",QueryCondition.EQ,Message.POSITION_SAVE));
//			queryList.add(new QueryCondition("sendFreeze",QueryCondition.EQ,Message.Status.N));
//			queryList.add(new QueryCondition("sender.id",QueryCondition.EQ,curUserId));
//			break;
//		case 2://发件箱
//			queryList.add(new QueryCondition("position",QueryCondition.EQ,Message.POSITION_SEND));
//			queryList.add(new QueryCondition("sendFreeze",QueryCondition.EQ,Message.Status.N));
//			queryList.add(new QueryCondition("sender.id",QueryCondition.EQ,curUserId));
//			break;
//		case 3://收件箱
////			queryList.add(new QueryCondition("position",QueryCondition.EQ,Message.POSITION_RECIVE));
//			queryList.add(new QueryCondition("receiveFreeze",QueryCondition.EQ,Message.Status.N));
//			queryList.add(new QueryCondition("type",QueryCondition.EQ,Message.Type.MSG));
//			
//			queryList.add(new QueryCondition("receiver.id",QueryCondition.EQ,curUserId));
//			break;
//		case 4://公告
////			queryList.add(new QueryCondition("position",QueryCondition.EQ,Message.POSITION_RECIVE));
//			queryList.add(new QueryCondition("receiveFreeze",QueryCondition.EQ,Message.Status.N));
//			queryList.add(new QueryCondition("type",QueryCondition.EQ,Message.Type.ADV));
//			
//			queryList.add(new QueryCondition("receiver.id",QueryCondition.EQ,curUserId));
//			break;
//		}
//		
//		pageResult.setQueryList(queryList);
//		pageResult.setOrderList(orderList);
		return super.pageResult(v, pageResult);
	}

	@Override
	public void update2freeze(String status,String flag,String ids) throws InvalidIdException,
			EntityNotFindException, GlobalException {
//		List<Message> msgList = messageDao.findByIds(ids);
//		for(Message p:msgList) {
//			if("0".equals(flag)){//发件箱、草稿箱
//				p.setSendFreeze("0".equals(status)?Message.Status.N:Message.Status.Y);
//			}else if("1".equals(flag)){//收件箱、公告
//				p.setReceiveFreeze("0".equals(status)?Message.Status.N:Message.Status.Y);
//			}else if("2".equals(flag)){
//				//操作人为收件人则恢复至收件箱、公告箱
//				if(userId.equals(p.getReceiver().getId())){
//					p.setReceiveFreeze(Message.Status.N);
//				}
//				//操作人为发件人则恢复至发件箱、草稿箱
//				if(userId.equals(p.getSender().getId())){
//					p.setSendFreeze(Message.Status.N);
//				}
//			}
//			messageDao.update(p);
//		}
	}
	
	@Override
	public void update2read(String ids) throws InvalidIdException,
			EntityNotFindException, GlobalException {
		List<Message> msgList = messageDao.findByIds(ids);
//		for(Message p:msgList) {
//			p.setReadFlag(Message.Status.Y);
//			p.setReadTime(DateUtils.getCurrDateTimeStr());
//			messageDao.update(p);
//		}
	}

	@Override
	public PageResult findOutBox(PageResult pageResult, AccountVo accountVo)
			throws GlobalException {
		return findMessageByPosition(pageResult, accountVo,Message.POSITION_SEND);
	}

	/** 
	 * 获取发送邮件数据
	 * @param pageResult 分页信息 
	 * @param accountVo 账户信息
	 * @param position 邮件状态<br>
	 * 			Message.POSITION_SEND 已发送（发件箱）
	 * 			Message.POSITION_SAVE 以保存（草稿箱）
	 * @return
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	private PageResult findMessageByPosition(PageResult pageResult,
			AccountVo accountVo,String position) throws GlobalException {
		// 邮件存在发件箱内（已发送）
		pageResult.addCondition("position", QueryCondition.EQ, position);
		// 邮件账户过滤
		pageResult.addCondition("sender.id",QueryCondition.EQ, accountVo.getId());
		
		pageResult = this.messageDao.getPageResult(pageResult);
		pageResult.setResultList(super.po2Vos((List<Message>)pageResult.getResultList()));
		return pageResult;
	}

	@Override
	public PageResult findRoughDraft(PageResult pageResult, AccountVo accountVo)
			throws GlobalException {
		return findMessageByPosition(pageResult,accountVo,Message.POSITION_SAVE);
	}

	@Override
	public void save2Rd(MessageVo v, AccountVo accountVo) throws GlobalException{
		List<AccountVo> accountVos = v.getTargetAccountVos();
		String[] accountIds = new String[accountVos.size()];
		for(int iIndex=0;iIndex<accountVos.size();iIndex++){
			accountIds[iIndex] = accountVos.get(iIndex).getId();
		}
		
		List<Account> accounts = accountDao.findByIds(accountIds);
		if(CollectionUtils.isEmpty(accounts)){
			throw new GlobalException("无效的收件人信息！");
		}
		
		Message message = super.vo2Po(v);
		message.setPosition(Message.POSITION_SAVE);
		
		// 设置当前操作人
		message.setSender(accountDao.findById(accountVo.getId()));
		for(Account account : accounts){
			message.getTargetAccounts().add(account);
		}
		messageDao.add(message);
	}

	@Override
	public void update2Rd(MessageVo v, AccountVo accountVo)
			throws GlobalException {
		// 验证，获取账户信息
		List<AccountVo> accountVos = v.getTargetAccountVos();
		String[] accountIds = new String[accountVos.size()];
		for(int iIndex=0;iIndex<accountVos.size();iIndex++){
			accountIds[iIndex] = accountVos.get(iIndex).getId();
		}
		
		List<Account> accounts = accountDao.findByIds(accountIds);
		if(CollectionUtils.isEmpty(accounts)){
			throw new GlobalException("无效的收件人信息！");
		}
		
		List<Account> removeAccount = new ArrayList<Account>();
		Message message = this.vo2Po(v);
		for(Account account : message.getTargetAccounts()){
			if(!accounts.contains(account)){
				removeAccount.add(account);
			}
		}
		
		message.getTargetAccounts().removeAll(removeAccount);
		for(Account account : accounts){
			if(!message.getTargetAccounts().contains(account)){
				message.getTargetAccounts().add(account);
			}
		}
		
		messageDao.update(message);
	}

	@Override
	public Message vo2Po(MessageVo v) throws GlobalException {
		Message message = messageDao.findById(v.getId());
		BeanUtils.copyProperties(v, message, new String[]{"id","createTime","isDel","version","position","senderVo","targetUserVoList"});
		return message;
	}

	
	@Override
	public void send(MessageVo v, AccountVo accountVo) throws GlobalException {
		if(StringUtils.hasText(v.getId())){	// 存在Id，更新发送
			this.send2Upd(v,accountVo);
		}else{	// 不存在Id，执行保存
			this.send2Save(v,accountVo);
		}
	}

	private void send2Save(MessageVo v, AccountVo accountVo) throws GlobalException {
		for(UserMessageVo userMessageVo : v.getTargetUserVoList()){
			v.getTargetAccountVos().add(userMessageVo.getAccountVo());
		}
		this.save2Rd(v, accountVo);
		this.send2Upd(v, accountVo);
	}

	private void send2Upd(MessageVo v, AccountVo accountVo) throws GlobalException{
		Message message = this.vo2Po(v);
		// 设置邮件状态为已发送
		message.setPosition(Message.POSITION_SEND);
		message.setSendTime(new Date());
		messageDao.update(message);
		addUserMessages(v,message,accountVo);
	}

	private void addUserMessages(MessageVo v,Message message, AccountVo accountVo) throws GlobalException{
		for(UserMessageVo userMessageVo : v.getTargetUserVoList()){
			UserMessage userMessage = new UserMessage();
			userMessage.setMessage(message);
			userMessage.setAccount(accountDao.findById(userMessageVo.getAccountVo().getId()));
			userMessageDao.add(userMessage);
		}
	}
	
}
