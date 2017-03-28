package com.yeedun.chinamobile.dao;

import java.util.List;
import java.util.Map;

import com.yeedun.chinamobile.model.MonthlyVoiceDetail;

public interface MonthlyVoiceDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyVoiceDetail record);

    int insertSelective(MonthlyVoiceDetail record);

    MonthlyVoiceDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyVoiceDetail record);

    int updateByPrimaryKey(MonthlyVoiceDetail record);
    
    int addBatch(List<MonthlyVoiceDetail> list);
    
    int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
}