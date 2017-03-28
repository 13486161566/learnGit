package com.yeedun.chinamobile.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.yeedun.chinamobile.bo.MonthlySmsDetailBO;
import com.yeedun.chinamobile.dao.MonthlySmsDetailMapper;
import com.yeedun.chinamobile.model.MonthlySmsDetail;

@Service("monthlySmsDetailBO")
public class MonthlySmsDetailBOImpl implements MonthlySmsDetailBO {
	
	public static Map<String, String> smsType = new HashMap<String, String>();
	static{
		smsType.put("01", "国内短信");
		smsType.put("02", "国际短信");
		smsType.put("03", "国内彩信");
		smsType.put("04", "国际漫游短信");
		smsType.put("05", "集团短信");
		smsType.put("06", "国际彩信");
	}
	
	@Resource
	private MonthlySmsDetailMapper monthlySmsDetailMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return monthlySmsDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthlySmsDetail record) {
		return monthlySmsDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlySmsDetail record) {
		return monthlySmsDetailMapper.insertSelective(record);
	}

	@Override
	public MonthlySmsDetail selectByPrimaryKey(Long id) {
		return monthlySmsDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlySmsDetail record) {
		return monthlySmsDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MonthlySmsDetail record) {
		return monthlySmsDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public int addBatch(List<MonthlySmsDetail> list) {
		return monthlySmsDetailMapper.addBatch(list);
	}

	@Override
	public int deleteBatch(Map<String, Object> map) {
		return monthlySmsDetailMapper.deleteBatch(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return monthlySmsDetailMapper.count(map);
	}
	
	@Override
	public List<MonthlySmsDetail> parseDetail(String htmlContent, String queryMonth, String phone, String isCurrent) {
		Document doc = Jsoup.parse(htmlContent);
		Elements tables = doc.getElementsByTag("table");
		Elements trs = tables.get(5).getElementsByTag("tr");
		List<MonthlySmsDetail> list = new ArrayList<MonthlySmsDetail>();
		for(int i = 1; i<trs.size(); i++){
			MonthlySmsDetail mvd = new MonthlySmsDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			Elements tds = trs.get(i).getElementsByTag("td");
			try {
				mvd.setSmsStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(tds.get(1).html().replaceAll("&nbsp;", ""), Locale.CHINA));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mvd.setSmsBusinessType(tds.get(2).html().replaceAll("&nbsp;", ""));
			mvd.setSmsIsDriving("收".equals(tds.get(3).html().replaceAll("&nbsp;", ""))?"1":"0");
			mvd.setSmsBusinessName(tds.get(4).html().replaceAll("&nbsp;", ""));
			mvd.setSmsOppCallNum(tds.get(5).html().replaceAll("&nbsp;", ""));
			mvd.setSmsOppArea(tds.get(6).html().replaceAll("&nbsp;", ""));
			mvd.setSmsContentLength(tds.get(7).html().replaceAll("&nbsp;", ""));
			mvd.setSmsFee(new BigDecimal(tds.get(8).html().replaceAll("&nbsp;", "")));
			mvd.setSmsFavorable(tds.get(9).html().replaceAll("&nbsp;", ""));
			list.add(mvd);
		}
		return list;
	}

	@Override
	public List<MonthlySmsDetail> parseDetail(JSONObject json,
			String queryMonth, String phone, String isCurrent) {
		List<MonthlySmsDetail> list = new ArrayList<MonthlySmsDetail>();
		JSONObject page = json.getJSONObject("pageMap");
		JSONArray arr =  page.getJSONArray("result");
		Iterator<Object> it = arr.iterator();
		JSONObject js = null;
		while(it.hasNext()){
			js = JSON.parseObject(it.next().toString());
			MonthlySmsDetail mvd = new MonthlySmsDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			try {
				mvd.setSmsStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(js.getString("smsdate")+" "+js.getString("smstime"), Locale.CHINA));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mvd.setSmsBusinessType(smsType.get(js.getString("businesstype")));
			
			mvd.setSmsIsDriving(("2".equals(js.getString("smstype"))?"0":"1"));
			//mvd.setSmsBusinessName();
			mvd.setSmsOppCallNum(js.getString("othernum"));
			//mvd.setSmsOppArea();
			//mvd.setSmsContentLength(tds.get(7).html().replaceAll("&nbsp;", ""));
			mvd.setSmsFee(new BigDecimal(js.getString("amount")));
			//mvd.setSmsFavorable(tds.get(9).html().replaceAll("&nbsp;", ""));
			list.add(mvd);
		}
		return list;
	}
}