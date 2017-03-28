package com.yeedun.chinamobile.dao;

import java.util.List;
import java.util.Map;

import com.yeedun.chinamobile.model.MonthlyVasDetail;

public interface MonthlyVasDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyVasDetail record);

    int insertSelective(MonthlyVasDetail record);

    MonthlyVasDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyVasDetail record);

    int updateByPrimaryKey(MonthlyVasDetail record);
    
    int addBatch(List<MonthlyVasDetail> list);
    
    int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
}