package com.yeedun.chinamobile.dao;

import java.util.List;
import java.util.Map;

import com.yeedun.chinamobile.model.MonthlyGprsDetail;

public interface MonthlyGprsDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyGprsDetail record);

    int insertSelective(MonthlyGprsDetail record);

    MonthlyGprsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyGprsDetail record);

    int updateByPrimaryKey(MonthlyGprsDetail record);
    
    int addBatch(List<MonthlyGprsDetail> list);
    
    int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
}