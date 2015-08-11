package cn.vant.base.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.UserMessageVo;

@Transactional
public interface IUserMessageService extends IBaseService<UserMessageVo> {

	/**
	 * 获取收件箱邮件列表
	 * @param pageResult 分页信息
	 * @param accountVo 账户信息 
	 * @return 分页的用户邮件数据
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	PageResult findInBox(PageResult pageResult, AccountVo accountVo) throws GlobalException;
	
	/**
	 * 根据邮件id获取邮件发送信息
	 * @param messageId 邮件id
	 * @return 邮件发送信息列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	List<UserMessageVo> findByMessageId(String messageId) throws GlobalException ;

	/**
	 * 获取公告邮件列表
	 * @param pageResult 分页信息
	 * @param accountVo	账户信息
	 * @return 分页的公告邮件数据
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	PageResult findGongGao(PageResult pageResult, AccountVo accountVo) throws GlobalException;

	/**
	 * 获取已删除的邮件信息
	 * @param pageResult 分页信息
	 * @param accountVo 账户信息
	 * @return 分页的以删除邮件信息
	 * @throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	PageResult findDeletedMessage(PageResult pageResult, AccountVo accountVo) throws GlobalException;
	
}
