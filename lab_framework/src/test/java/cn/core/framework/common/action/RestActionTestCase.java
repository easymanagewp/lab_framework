package cn.core.framework.common.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring-hibernate.xml","classpath:spring-mvc.xml" })
public class RestActionTestCase {
	
	@Autowired private WebApplicationContext wac;
	
	private MockMvc mockMvc; 
	
	@Before
	public void setup(){
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
	}
	
	@Test
	public void addTestCase() throws Exception{
		mockMvc.perform(
			MockMvcRequestBuilders.post("/users")
			.param("username", "wangpeng")
			.param("password", "password")
		).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void updateTestCase() throws Exception{
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users")
				.param("username", "wangpeng")
				.param("password", "password")
			).andExpect(MockMvcResultMatchers.status().isOk());
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users")
				.param("username", "wangpeng2")
				.param("password", "password2")
			).andExpect(MockMvcResultMatchers.status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void pagingTestCase() throws Exception{
		mockMvc.perform(
			MockMvcRequestBuilders.get("/users")
			.param("paging", "true")
		).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}
