package com.yeedun.chinamobile.dao;

import java.util.List;
import java.util.Map;

import com.yeedun.chinamobile.model.MonthlyRechargeDetail;

public interface MonthlyRechargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyRechargeDetail record);

    int insertSelective(MonthlyRechargeDetail record);

    MonthlyRechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyRechargeDetail record);

    int updateByPrimaryKey(MonthlyRechargeDetail record);
    
    int addBatch(List<MonthlyRechargeDetail> list);
    
    int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
}