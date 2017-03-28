package com.yeedun.chinamobile.bo;

import java.util.List;

import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlyBill;

public interface MonthlyBillBO extends BO{
	
	int deleteByPrimaryKey(Long id);

    int insert(MonthlyBill record);

    int insertSelective(MonthlyBill record);

    MonthlyBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyBill record);

    int updateByPrimaryKey(MonthlyBill record);
    
    int addBatch(List<MonthlyBill> list);
    
    MonthlyBill parseBill(String content, String isCurrentMonth, String phone, String queryMonth);

}
