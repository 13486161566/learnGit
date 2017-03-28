package com.yeedun.chinamobile.dao;

import java.util.Map;

import com.yeedun.chinamobile.model.Provinces;

public interface ProvincesMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Provinces record);

    int insertSelective(Provinces record);

    Provinces selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Provinces record);

    int updateByPrimaryKey(Provinces record);
    
    Provinces get(Map<String, String> map);
}