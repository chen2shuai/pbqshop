package com.shop.web.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.shop.web.entity.ShopUser;

@Repository(value="shopUserMapper")
public interface ShopUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    ShopUser selectByPrimaryKey(Integer userid);
    
    List<ShopUser> selectByName(String name);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);
    
    List<ShopUser> queryWithPage(JSONObject jsonObject);
}