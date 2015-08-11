package cn.core.framework.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.core.framework.common.page.OrderCondition;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.common.po.User;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring-hibernate.xml" })  
public class DaoTestCase {

	@Autowired private IUserDao userDao;
	
	@Before
	public void before(){
		Assert.assertNotNull(userDao);
	}
	
	private User add(int sort){
		User user = new User();
		user.setUsername("testUserName");
		user.setPassword("testPassword");
		user.setSort(sort);
		userDao.add(user);
		return user;
	}
	
	@Test
	public void addTestCase(){
		User user = add(1);
		
		List<User> users = userDao.find();
		Assert.assertEquals(1, users.size());
		Assert.assertNotNull(users.get(0).getId());
		Assert.assertEquals(32, users.get(0).getId().length());
		Assert.assertEquals(user.getId(), users.get(0).getId());
		Assert.assertEquals("testUserName", users.get(0).getUsername());
		Assert.assertEquals("testPassword", users.get(0).getPassword());
	}
	
	@Test
	public void queryTestCase(){
		User user1 = add(1);
		add(2);
		
		List<QueryCondition> queryConditions = new ArrayList<QueryCondition>();
		queryConditions.add(new QueryCondition("username", QueryCondition.EQ, "testUserName"));
		
		List<User> users = userDao.query(queryConditions, null, 0, 0);
		
		Assert.assertEquals(2, users.size());
		
		
		add(3);
		List<OrderCondition> orderCondition = new ArrayList<OrderCondition>();
		orderCondition.add(new OrderCondition("asc","sort"));
		
		users = userDao.query(queryConditions, orderCondition, 0, 0);
		Assert.assertEquals(3, users.size());
		Assert.assertEquals(1, users.get(0).getSort().intValue());
		Assert.assertEquals(2, users.get(1).getSort().intValue());
		Assert.assertEquals(3, users.get(2).getSort().intValue());
		
		User user = add(4);
		users = userDao.query(queryConditions, orderCondition, 0, 2);
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(1, users.get(0).getSort().intValue());
		Assert.assertEquals(2, users.get(1).getSort().intValue());
		
		userDao.update(user1);
		userDao.update(user);
		orderCondition.add(0,new OrderCondition("desc","lastUpdTime"));
		users = userDao.query(queryConditions, orderCondition, 0, 2);
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(4, users.get(0).getSort().intValue());
		Assert.assertEquals(1, users.get(1).getSort().intValue());
		Assert.assertEquals(user.getId(), users.get(0).getId());
	}
	
	
	
}
