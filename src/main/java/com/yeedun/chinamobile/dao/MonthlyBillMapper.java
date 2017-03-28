package com.yeedun.chinamobile.dao;

import java.util.List;
import java.util.Map;

import com.yeedun.chinamobile.model.MonthlyBill;

public interface MonthlyBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyBill record);

    int insertSelective(MonthlyBill record);

    MonthlyBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyBill record);

    int updateByPrimaryKey(MonthlyBill record);
    
    int addBatch(List<MonthlyBill> list);
    
    int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
}