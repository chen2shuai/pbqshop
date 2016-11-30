package com.shop.web.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.shop.web.entity.ShopUser;
import com.shop.web.service.ShopUserService;
import com.shop.web.util.DateUtil;
/**
 * service、dao层的测试类
 * @author chenershuai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类 
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml","classpath:applicationContext-service.xml","classpath:applicationContext-transaction.xml"})
public class ShopControllerTest {
	
	private static Logger logger = Logger.getLogger(ShopControllerTest.class);
	
	@Resource
	private ShopUserService shopUserService;
	
	@Transactional
	@Test
	public void getShopUserById(){
		ShopUser shopUser = new ShopUser();
		shopUser.setUserid(6);
		shopUser.setUsername("zhangsan");
		shopUser.setPassword("333");
		shopUser.setCreateTime(Long.parseLong(DateUtil.getString(new Date(), DateUtil.YMDHMS)));
		try {
			shopUserService.insertSelective(shopUser);
			int i = 2/0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ShopControllerTest" + e);
		}
		
		logger.info(JSON.toJSONString("*********"));
	}
}
