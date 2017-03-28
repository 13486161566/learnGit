package com.yeedun.chinamobile.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yeedun.chinamobile.bo.MonthlyRechargeDetailBO;
import com.yeedun.chinamobile.dao.MonthlyRechargeDetailMapper;
import com.yeedun.chinamobile.model.MonthlyRechargeDetail;

@Service
public class MonthlyRechargeBOImpl implements MonthlyRechargeDetailBO {

	@Resource
	private MonthlyRechargeDetailMapper monthlyRechargeDetailMapper;
	
	@Override
	public int deleteBatch(Map<String, Object> map) {
		return monthlyRechargeDetailMapper.deleteBatch(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return monthlyRechargeDetailMapper.count(map);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return monthlyRechargeDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthlyRechargeDetail record) {
		return monthlyRechargeDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlyRechargeDetail record) {
		return monthlyRechargeDetailMapper.insertSelective(record);
	}

	@Override
	public MonthlyRechargeDetail selectByPrimaryKey(Long id) {
		return monthlyRechargeDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlyRechargeDetail record) {
		return monthlyRechargeDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MonthlyRechargeDetail record) {
		return monthlyRechargeDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public int addBatch(List<MonthlyRechargeDetail> list) {
		return monthlyRechargeDetailMapper.addBatch(list);
	}

	@Override
	public List<MonthlyRechargeDetail> parseDetail(String htmlContent, String queryMonth, String phone, String isCurrent) {
		List<MonthlyRechargeDetail> list = new ArrayList<MonthlyRechargeDetail>();
		Document doc = Jsoup.parse(htmlContent);
		Elements trs = doc.getElementsByTag("table").get(13).getElementsByTag("tr");
		Date queryDate = new Date();
		Elements tds = null;
		for(int i = 1; i< trs.size(); i++){
			tds = trs.get(i).getElementsByTag("td");
			MonthlyRechargeDetail mrd = new MonthlyRechargeDetail();
			mrd.setPhone(phone);
			mrd.setQueryDate(queryDate);
			mrd.setQueryMonth(queryMonth);
			try {
				mrd.setRechargeDate(new SimpleDateFormat("yyyy-MM-dd").parse(tds.get(0).text()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mrd.setRechargeWay(tds.get(1).text());
			mrd.setCapital(new BigDecimal(tds.get(2).text().substring(0, tds.get(2).text().length()-2)));
			mrd.setPresenter(new BigDecimal(tds.get(3).text().substring(0, tds.get(3).text().length()-2)));
			mrd.setFavorable(tds.get(4).text());
			mrd.setRemark(tds.get(5).text());
			list.add(mrd);
		}
		return list;
	}

	@Override
	public List<MonthlyRechargeDetail> parseDetail(JSONObject json,
			String queryMonth, String phone, String isCurrent) {
		// TODO Auto-generated method stub
		return null;
	}
}
