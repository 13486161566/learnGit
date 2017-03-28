package com.yeedun.chinamobile.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSONObject;
import com.yeedun.chinamobile.bo.MonthlyVasDetailBO;
import com.yeedun.chinamobile.dao.MonthlyVasDetailMapper;
import com.yeedun.chinamobile.model.MonthlyVasDetail;

@Service("monthlyVasDetailBO")
public class MonthlyVasDetailBOImpl implements MonthlyVasDetailBO {
	
	@Resource
	private MonthlyVasDetailMapper monthlyVasDetailMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return monthlyVasDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthlyVasDetail record) {
		return monthlyVasDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlyVasDetail record) {
		return monthlyVasDetailMapper.insertSelective(record);
	}

	@Override
	public MonthlyVasDetail selectByPrimaryKey(Long id) {
		return monthlyVasDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlyVasDetail record) {
		return monthlyVasDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MonthlyVasDetail record) {
		return monthlyVasDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public int addBatch(List<MonthlyVasDetail> list) {
		return monthlyVasDetailMapper.addBatch(list);
	}

	@Override
	public int deleteBatch(Map<String, Object> map) {
		return monthlyVasDetailMapper.deleteBatch(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return monthlyVasDetailMapper.count(map);
	}

	@Override
	public List<MonthlyVasDetail> parseDetail(String htmlContent,
			String queryMonth, String phone, String isCurrent) {
		Document doc = Jsoup.parse(htmlContent);
		Elements tables = doc.getElementsByTag("table");
		Elements trs = tables.get(5).getElementsByTag("tr");
		List<MonthlyVasDetail> list = new ArrayList<MonthlyVasDetail>();
		for(int i = 1; i<trs.size(); i++){
			MonthlyVasDetail mvd = new MonthlyVasDetail();
			mvd.setQueryMonth(queryMonth);
			mvd.setPhone(phone);
			mvd.setQueryDate(new Date());
			mvd.setIsCerrentMonth(isCurrent);
			Elements tds = trs.get(i).getElementsByTag("td");
			try {
				mvd.setVasStartTime(new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(tds.get(1).html().replaceAll("&nbsp;", ""), Locale.CHINA));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mvd.setVasCostDate(tds.get(2).html().replaceAll("&nbsp;", ""));
			mvd.setVasProductName(tds.get(3).html().replaceAll("&nbsp;", ""));
			mvd.setVasBusinessCode(tds.get(4).html().replaceAll("&nbsp;", ""));
			mvd.setVasBusinessName(tds.get(5).html().replaceAll("&nbsp;", ""));
			mvd.setVasBusinessType(tds.get(6).html().replaceAll("&nbsp;", ""));
			mvd.setVasCostType(tds.get(7).html().replaceAll("&nbsp;", ""));
			mvd.setVasFee(new BigDecimal(StringUtil.isBlank(tds.get(8).html().replaceAll("&nbsp;", ""))?"0.00":tds.get(8).html().replaceAll("&nbsp;", "")));
			list.add(mvd);
		}
		return list;
	}

	@Override
	public List<MonthlyVasDetail> parseDetail(JSONObject json,
			String queryMonth, String phone, String isCurrent) {
		// TODO Auto-generated method stub
		return null;
	}
}