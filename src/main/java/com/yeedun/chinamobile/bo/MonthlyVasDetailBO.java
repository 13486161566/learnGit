package com.yeedun.chinamobile.bo;

import java.util.List;

import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlyVasDetail;

public interface MonthlyVasDetailBO extends BO<MonthlyVasDetail>{
	
	int deleteByPrimaryKey(Long id);

    int insert(MonthlyVasDetail record);

    int insertSelective(MonthlyVasDetail record);

    MonthlyVasDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyVasDetail record);

    int updateByPrimaryKey(MonthlyVasDetail record);
    
    int addBatch(List<MonthlyVasDetail> list);
}