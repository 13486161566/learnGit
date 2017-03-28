package com.yeedun.chinamobile.common;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface BO<T> {
	
	int deleteBatch(Map<String, Object> map);
    
    int count(Map<String, Object> map);
    
    List<T> parseDetail(String htmlContent, String queryMonth, String phone, String isCurrent);
    
    List<T> parseDetail(JSONObject json, String queryMonth, String phone, String isCurrent);
}
