package com.yeedun.chinamobile.bo;

import java.util.List;

import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlyGprsDetail;

public interface MonthlyGprsDetailBO extends BO<MonthlyGprsDetail> {
	
	int deleteByPrimaryKey(Long id);

    int insert(MonthlyGprsDetail record);

    int insertSelective(MonthlyGprsDetail record);

    MonthlyGprsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyGprsDetail record);

    int updateByPrimaryKey(MonthlyGprsDetail record);
    
    int addBatch(List<MonthlyGprsDetail> list);
}