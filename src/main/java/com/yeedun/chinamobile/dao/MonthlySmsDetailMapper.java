package com.yeedun.chinamobile.dao;

import java.util.List;
import java.util.Map;

import com.yeedun.chinamobile.model.MonthlySmsDetail;

public interface MonthlySmsDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlySmsDetail record);

    int insertSelective(MonthlySmsDetail record);

    MonthlySmsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlySmsDetail record);

    int updateByPrimaryKey(MonthlySmsDetail record);
    
    int addBatch(List<MonthlySmsDetail> list);
    
    int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
}