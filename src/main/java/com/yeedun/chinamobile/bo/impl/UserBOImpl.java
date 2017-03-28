package com.yeedun.chinamobile.bo.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yeedun.chinamobile.bo.UserBO;
import com.yeedun.chinamobile.dao.UserMapper;
import com.yeedun.chinamobile.model.User;

@Service("userBO")
public class UserBOImpl implements UserBO {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return 0;
	}

	@Override
	public User get(Map<String, String> map) {
		return userMapper.get(map);
	}
	
}