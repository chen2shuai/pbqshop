package com.shop.web.test;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.shop.web.controller.LoginController;
//此处调用Spring单元测试类
@RunWith(SpringJUnit4ClassRunner.class)
//调用javaWEB的组件，比如自动注入ServletContext Bean等等
@WebAppConfiguration
//加载Spring配置文件
@ContextConfiguration(locations = { "classpath:pbqshop-servlet.xml","classpath:applicationContext-service.xml","classpath:applicationContext-mybatis.xml" })

public class ShopControllerTest2 {

	private static Logger logger = Logger.getLogger(ShopControllerTest2.class);
	
	@Autowired
	LoginController shopController;

	@Autowired
	ServletContext context;
	
	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
	}

	@Test
	public void getArticleListTest() {

		// 发送请求
		ResultActions resultActions;
		String result = null;
		//ResultAction是用来模拟Browser发送FORM表单请求的;post()是请求的地址;
		//accept()请求的内容 param()请求的键值对，如果有多个参数可以后缀调用多个param();
		//MvcResult是获得服务器的Response内容。
		try {
			resultActions = this.mockMvc.perform(MockMvcRequestBuilders
					.post("/user/login.htm"));
			MvcResult mvcResult = resultActions.andReturn();
			result = mvcResult.getResponse().getContentAsString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("=====客户端获得反馈数据:" + result);
	}
}
