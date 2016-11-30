package com.shop.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.web.service.ShopUserService;

@Controller
public class VelocityController {

	private static Logger logger = Logger.getLogger(VelocityController.class);

	@Resource
	private ShopUserService shopUserService;

	@RequestMapping(value = "/listPhoto.htm")
	public ModelAndView LoginRequest(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("photo");
		return mv;
	}
}
