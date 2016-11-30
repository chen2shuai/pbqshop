package com.shop.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.shop.web.dao.ShopUserMapper;
import com.shop.web.entity.ShopUser;
import com.shop.web.service.ShopUserService;

@Service(value="shopUserService")
public class ShopUserServiceImpl implements ShopUserService{
	
	@Resource
	private ShopUserMapper shopUserMapper;

	//通过id查询数据
	@Override
	public ShopUser getShopUserById(int userid) {
		ShopUser shopUser = shopUserMapper.selectByPrimaryKey(userid);
		return shopUser;
	}
	
	// 通过name查询数据
	@Override
	public List<ShopUser> listShopUserByName(String username) {
		List<ShopUser> shopUserList = shopUserMapper.selectByName(username);
		return shopUserList;
	}
	
	//查询所有的数据
	@Override
	public List<ShopUser> quyeryShopUserWithPage(JSONObject jsonObject) {
		ArrayList<ShopUser> userlist = new ArrayList<ShopUser>();
		if(!jsonObject.isNullObject() && !jsonObject.isEmpty()){
			userlist = (ArrayList<ShopUser>) shopUserMapper.queryWithPage(jsonObject);
		}
		return userlist;
	}

	@Override
	public void insert(ShopUser shopUser) {
		shopUserMapper.insert(shopUser);
	}

	@Override
	public void insertSelective(ShopUser shopUser) {
		shopUserMapper.insertSelective(shopUser);
	}
	
}
