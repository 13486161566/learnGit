package com.yeedun.chinamobile.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yeedun.chinamobile.bo.MonthlyVoiceDetailBO;
import com.yeedun.chinamobile.dao.MonthlyVoiceDetailMapper;
import com.yeedun.chinamobile.model.MonthlyVoiceDetail;

@Service("monthlyVoiceDetailBO")
public class MonthlyVoiceDetailBOImpl implements MonthlyVoiceDetailBO {
	
	@Resource
	private MonthlyVoiceDetailMapper monthlyVoiceDetailMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return monthlyVoiceDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthlyVoiceDetail record) {
		return monthlyVoiceDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlyVoiceDetail record) {
		return monthlyVoiceDetailMapper.insertSelective(record);
	}

	@Override
	public MonthlyVoiceDetail selectByPrimaryKey(Long id) {
		return monthlyVoiceDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlyVoiceDetail record) {
		return monthlyVoiceDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MonthlyVoiceDetail record) {
		return monthlyVoiceDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public int addBatch(List<MonthlyVoiceDetail> list) {
		return monthlyVoiceDetailMapper.addBatch(list);
	}

	@Override
	public int deleteBatch(Map<String, Object> map) {
		return monthlyVoiceDetailMapper.deleteBatch(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return monthlyVoiceDetailMapper.count(map);
	}

	@Override
	public List<MonthlyVoiceDetail> parseDetail(String htmlContent,
			String queryMonth, String phone, String isCurrent) {
		Document doc = Jsoup.parse(htmlContent);
		Elements tables = doc.getElementsByTag("table");
		Elements trs = tables.get(5).getElementsByTag("tr");
		List<MonthlyVoiceDetail> list = new ArrayList<MonthlyVoiceDetail>();
		for(int i = 1; i<trs.size(); i++){
			MonthlyVoiceDetail mvd = new MonthlyVoiceDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			Elements tds = trs.get(i).getElementsByTag("td");
			try {
				mvd.setVoiceStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(tds.get(1).html().replaceAll("&nbsp;", ""), Locale.CHINA));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mvd.setVoiceHoidingTime(tds.get(2).html().replaceAll("&nbsp;", ""));
			mvd.setVoiceCallArea(tds.get(3).html().replaceAll("&nbsp;", ""));
			mvd.setVoiceType("主叫".endsWith(tds.get(4).html().replaceAll("&nbsp;", ""))?"1":"2");
			mvd.setVoiceOppCallNum(tds.get(5).html().replaceAll("&nbsp;", ""));
			mvd.setVoiceOppNumType(tds.get(6).html().replaceAll("&nbsp;", ""));
			mvd.setVoiceOppArea(tds.get(7).html().replaceAll("&nbsp;", ""));
			mvd.setVoiceBusinessName(tds.get(8).html().replaceAll("&nbsp;", ""));
			mvd.setVoiceCallBaseFee(new BigDecimal(tds.get(9).html().replaceAll("&nbsp;", "")));
			mvd.setVoiceLongCallFee(new BigDecimal(tds.get(10).html().replaceAll("&nbsp;", "")));
			mvd.setVoiceCallInfoFee(new BigDecimal(tds.get(11).html().replaceAll("&nbsp;", "")));
			mvd.setVoiceFavorable(tds.get(12).html().replaceAll("&nbsp;", ""));
			list.add(mvd);
		}
		return list;
	}

	@Override
	public List<MonthlyVoiceDetail> parseDetail(JSONObject json,
			String queryMonth, String phone, String isCurrent) {
		List<MonthlyVoiceDetail> list = new ArrayList<MonthlyVoiceDetail>();
		JSONObject page = json.getJSONObject("pageMap");
		JSONArray arr =  page.getJSONArray("result");
		Iterator<Object> it = arr.iterator();
		JSONObject js = null;
		while(it.hasNext()){
			js = JSON.parseObject(it.next().toString());
			MonthlyVoiceDetail mvd = new MonthlyVoiceDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			try {
				mvd.setVoiceStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(js.getString("calldate")+" "+js.getString("calltime"), Locale.CHINA));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			mvd.setVoiceHoidingTime(js.getString("calllonghour"));
			mvd.setVoiceCallArea(js.getString("homeareaName"));
			mvd.setVoiceType("1".endsWith(js.getString("calltype"))?"1":"2");
			mvd.setVoiceOppCallNum(js.getString("othernum"));
//			mvd.setVoiceOppNumType();
			mvd.setVoiceOppArea(js.getString("calledhome"));
//			mvd.setVoiceBusinessName();
			mvd.setVoiceCallBaseFee(new BigDecimal(js.getString("nativefee")));
			mvd.setVoiceLongCallFee(new BigDecimal(js.getString("roamfee")));
			mvd.setVoiceCallInfoFee(new BigDecimal(js.getString("landfee")).add(new BigDecimal(js.getString("otherfee"))).subtract(new BigDecimal(js.getString("deratefee"))));
//			mvd.setVoiceFavorable();
			list.add(mvd);
		}
		return list;
	}
}