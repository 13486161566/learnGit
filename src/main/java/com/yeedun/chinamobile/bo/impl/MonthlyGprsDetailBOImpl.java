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
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yeedun.chinamobile.bo.MonthlyGprsDetailBO;
import com.yeedun.chinamobile.dao.MonthlyGprsDetailMapper;
import com.yeedun.chinamobile.model.MonthlyGprsDetail;

@Service("monthlyGprsDetailBO")
public class MonthlyGprsDetailBOImpl implements MonthlyGprsDetailBO{
	
	@Resource
	private MonthlyGprsDetailMapper monthlyGprsDetailMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return monthlyGprsDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthlyGprsDetail record) {
		return monthlyGprsDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlyGprsDetail record) {
		return monthlyGprsDetailMapper.insertSelective(record);
	}

	@Override
	public MonthlyGprsDetail selectByPrimaryKey(Long id) {
		return monthlyGprsDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlyGprsDetail record) {
		return monthlyGprsDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MonthlyGprsDetail record) {
		return monthlyGprsDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public int addBatch(List<MonthlyGprsDetail> list) {
		return monthlyGprsDetailMapper.addBatch(list);
	}

	@Override
	public int deleteBatch(Map<String, Object> map) {
		return monthlyGprsDetailMapper.deleteBatch(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return monthlyGprsDetailMapper.count(map);
	}

	@Override
	public List<MonthlyGprsDetail> parseDetail(String htmlContent,
			String queryMonth, String phone, String isCurrent) {
		Document doc = Jsoup.parse(htmlContent);
		Elements tables = doc.getElementsByTag("table");
		Elements trs = tables.get(5).getElementsByTag("tr");
		List<MonthlyGprsDetail> list = new ArrayList<MonthlyGprsDetail>();
		for(int i = 1; i<trs.size(); i++){
			MonthlyGprsDetail mvd = new MonthlyGprsDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			Elements tds = trs.get(i).getElementsByTag("td");
			try {
				mvd.setGprsStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(tds.get(1).html().replaceAll("&nbsp;", ""), Locale.CHINA));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mvd.setGprsBusinessType(tds.get(2).html().replaceAll("&nbsp;", ""));
			mvd.setGprsBusinessName(tds.get(3).html().replaceAll("&nbsp;", ""));
			mvd.setGprsNetworkIdentify(tds.get(4).html().replaceAll("&nbsp;", ""));
			mvd.setGprsUseArea(tds.get(5).html().replaceAll("&nbsp;", ""));
			mvd.setGprsContentLength(tds.get(6).html().replaceAll("&nbsp;", ""));
			mvd.setGprsCostTime(tds.get(7).html().replaceAll("&nbsp;", ""));
			mvd.setGprsFee(new BigDecimal(StringUtil.isBlank(tds.get(8).html().replaceAll("&nbsp;", ""))?"0.00":tds.get(8).html().replaceAll("&nbsp;", "")));
			mvd.setGprsFavorable(tds.get(9).html().replaceAll("&nbsp;", ""));
			list.add(mvd);
		}
		return list;
	}

	@Override
	public List<MonthlyGprsDetail> parseDetail(JSONObject json,
			String queryMonth, String phone, String isCurrent) {
		List<MonthlyGprsDetail> list = new ArrayList<MonthlyGprsDetail>();
		
		JSONArray jsArr = json.getJSONArray("pagelist");
		Iterator<Object> it = jsArr.iterator();
		JSONObject js = null;
		while(it.hasNext()){
			MonthlyGprsDetail mvd = new MonthlyGprsDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			
			js = JSON.parseObject(it.next().toString());
			try {
				mvd.setGprsStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(js.getString("begindateformat")+" "+js.getString("begintimeformat"), Locale.CHINA));
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			mvd.setGprsBusinessType(tds.get(2).html().replaceAll("&nbsp;", ""));
			mvd.setGprsBusinessName(js.getString("forwardtypeformat"));
//			mvd.setGprsNetworkIdentify(tds.get(4).html().replaceAll("&nbsp;", ""));
			mvd.setGprsUseArea(js.getString("homearea"));
			mvd.setGprsContentLength(js.getString("pertotalsm"));
			mvd.setGprsCostTime(js.getString("longhour"));
			mvd.setGprsFee(new BigDecimal(js.getString("totalfee")));
			mvd.setGprsFavorable(js.getString("svcname"));
			list.add(mvd);
		}
		return list;
	}
	
}