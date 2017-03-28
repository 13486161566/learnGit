package com.yeedun.chinamobile.bo;

import java.util.List;

import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlySmsDetail;

public interface MonthlySmsDetailBO extends BO<MonthlySmsDetail>{
	
	int deleteByPrimaryKey(Long id);

    int insert(MonthlySmsDetail record);

    int insertSelective(MonthlySmsDetail record);

    MonthlySmsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlySmsDetail record);

    int updateByPrimaryKey(MonthlySmsDetail record);
    
    int addBatch(List<MonthlySmsDetail> list);
}