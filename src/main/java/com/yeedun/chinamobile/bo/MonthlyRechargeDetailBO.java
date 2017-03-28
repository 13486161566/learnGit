package com.yeedun.chinamobile.bo;

import java.util.List;

import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlyRechargeDetail;

public interface MonthlyRechargeDetailBO extends BO<MonthlyRechargeDetail> {
	
	int deleteByPrimaryKey(Long id);

    int insert(MonthlyRechargeDetail record);

    int insertSelective(MonthlyRechargeDetail record);

    MonthlyRechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyRechargeDetail record);

    int updateByPrimaryKey(MonthlyRechargeDetail record);
    
    int addBatch(List<MonthlyRechargeDetail> list);

}
