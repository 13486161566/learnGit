package com.yeedun.chinamobile.bo;

import java.util.List;

import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlyVoiceDetail;

public interface MonthlyVoiceDetailBO extends BO<MonthlyVoiceDetail>{
	
	int deleteByPrimaryKey(Long id);

    int insert(MonthlyVoiceDetail record);

    int insertSelective(MonthlyVoiceDetail record);

    MonthlyVoiceDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyVoiceDetail record);

    int updateByPrimaryKey(MonthlyVoiceDetail record);
    
    int addBatch(List<MonthlyVoiceDetail> list);
}