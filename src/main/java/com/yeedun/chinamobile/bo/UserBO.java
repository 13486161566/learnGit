package com.yeedun.chinamobile.bo;

import java.util.Map;

import com.yeedun.chinamobile.model.User;

public interface UserBO {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User get(Map<String, String> map);
}