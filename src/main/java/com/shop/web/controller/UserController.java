package com.shop.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.web.entity.DataGrid;
import com.shop.web.entity.ShopUser;
import com.shop.web.service.ShopUserService;

@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private ShopUserService shopUserService;

	private String usernames;

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

	@RequestMapping(value = "/listUser.htm")
	public ModelAndView LoginRequest(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/listUser");
		return mv;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param page 页码
	 * @param rows 每页的大小（条数）
	 * @return
	 */
	@RequestMapping(value = "/queryListUser.json")
	@ResponseBody
	public DataGrid quyerListUser(HttpServletRequest request,HttpServletResponse response,int page,int rows) {
		String paramsObj = request.getParameter("params");
		JSONObject paramsJson  = JSONObject.fromObject(paramsObj);
		paramsJson.put("page", page);
		paramsJson.put("rows", rows);
		List<ShopUser> shopUsers = new ArrayList<ShopUser>();
		try {
			shopUsers = shopUserService.quyeryShopUserWithPage(paramsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return new DataGrid(shopUsers);
	}
}
