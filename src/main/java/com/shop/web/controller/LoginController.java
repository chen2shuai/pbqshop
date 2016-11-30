package com.shop.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.web.entity.ShopUser;
import com.shop.web.service.ShopUserService;

@Controller
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Resource
	private ShopUserService shopUserService;
	
	private String usernames;

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

	@RequestMapping(value = "/login.htm")
	public ModelAndView LoginRequest(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<ShopUser> shopUserList = new ArrayList<ShopUser>();
		if (StringUtils.isNotBlank(username)) {
			shopUserList = shopUserService.listShopUserByName(username);
		}
		if (shopUserList.size() > 0) {
			for (ShopUser shopUser : shopUserList) {
				String password2 = shopUser.getPassword();
				if (password.equals(password2)) {
					// 登录成功
					mv.addObject("shopUser", shopUser);
					mv.addObject("username",shopUser.getUsername());
					mv.setViewName("mainPage");
					break;
				} else {
					// 密码错误
					mv.addObject("password", password2);
					mv.addObject("loginMsg", "erorrPassWord");//密码错误，可以试试短信登录 ,或者找回密码	
					mv.setViewName("login");
				}
			}
		} else {
			// 没有此用户
			mv.addObject("username", username);
			mv.addObject("loginMsg", "errorUserName");//您输入的帐号不存在，可查看帮助或立即注册
			mv.setViewName("login");
		}
		
		return mv;
	}
}
