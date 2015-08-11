package cn.vant.base.system.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.page.PageResult;
import cn.core.framework.common.service.IBaseService;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.exception.InvalidIdException;
import cn.vant.base.system.vo.AccountVo;
import cn.vant.base.system.vo.MessageVo;
/**
 * <strong>创建信息:2015年7月8日 下午3:55:34 </strong> <br>
 * <strong>概要 : 站内信service</strong> <br>
 */
@Transactional
public interface IMessageService extends IBaseService<MessageVo> {
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:07:23 </strong> <br>
	 * <strong>概要 : 调整站内信状态,操作人为发件人恢复至发件箱，操作人为收件人恢复至收件箱</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param status 状态 0 未读 1 已读
	 *@param flag 标识  0-发件箱、草稿箱 1-收件箱、公告 2-收件箱
	 *@param ids ids 如(1,2,3,4)
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update2freeze(String status,String flag,String ids) throws InvalidIdException,EntityNotFindException,GlobalException;
	/**
	 * <strong>创建信息: 2015年7月8日 上午11:11:45 </strong> <br>
	 * <strong>概要 : 标记为已读</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param ids ids 如(1,2,3,4)
	 *@throws InvalidIdException
	 *@throws EntityNotFindException
	 *@throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update2read(String ids) throws InvalidIdException,EntityNotFindException,GlobalException;
	
	/**
	 * 获取发件箱信息
	 * @param pageResult 分页请求信息实体
	 * @param accountVo 用户账户信息，获取哪个账户下的邮件列表
	 * @return 分页数据
	 * @throws GlobalException
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageResult findOutBox(PageResult pageResult, AccountVo accountVo) throws GlobalException;
	
	/**
	 * 获取草稿箱信息
	 * @param pageResult 分页请求信息
	 * @param accountVo 用户账户信息
	 * @return 分页邮件数据
	 * @throws GlobalException 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageResult findRoughDraft(PageResult pageResult, AccountVo accountVo) throws GlobalException;
	
	/**
	 * 保存邮件至草稿箱
	 * @param v 邮件信息
	 * @param accountVo 账户信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void save2Rd(MessageVo v, AccountVo accountVo) throws GlobalException;
	
	/**
	 * 更新邮件草稿信息
	 * @param v 邮件草稿
	 * @param accountVo 账户信息
	 * @throws GlobalException
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update2Rd(MessageVo v, AccountVo accountVo) throws GlobalException;
	
	/**
	 * 发送邮件
	 * @param v 发送邮件信息
	 * @param accountVo 发送账户信息
	 * @throws GlobalException 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void send(MessageVo v,AccountVo accountVo) throws GlobalException;
	
	
	
}
