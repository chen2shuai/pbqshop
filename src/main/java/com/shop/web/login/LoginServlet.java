package com.shop.web.login;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.shop.web.entity.ShopUser;
import com.shop.web.service.ShopUserService;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	Logger logger = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		logger.info("LoginServlet init() 初始化");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("LoginServlet doPost() post请求处理业务");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<ShopUser> userList = null;
		//用户是否存在：true存在，false不存在
		if (userList.size() != 0) {
			boolean flag = false;
			for (int i = 0; i < userList.size(); i++) {
				String username2 = userList.get(i).getUsername();
				String password2 = userList.get(i).getPassword();
				if(StringUtils.isNotEmpty(username2) && StringUtils.isNotBlank(password2) && username2.equals(username)){
					flag = true;
					if(password2.equals(password)){
						
						//登录成功
					}else{
						
						//密码错误
					}
					break;
				}
			}
			
			if(!flag){
				//用户不存在
				
			}
		} else {
			// 不存在用户信息

		}
	}

	@Override
	public void destroy() {
		logger.info("LoginServlet destroy() 释放占用的资源");
	}

}
