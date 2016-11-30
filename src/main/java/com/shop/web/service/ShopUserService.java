package com.shop.web.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.shop.web.entity.ShopUser;

public interface ShopUserService {
	public ShopUser getShopUserById(int userid);
	public List<ShopUser> quyeryShopUserWithPage(JSONObject jsonObject);
	public List<ShopUser> listShopUserByName(String username);
	public void insert(ShopUser shopUser);
	public void insertSelective(ShopUser shopUser);
}
