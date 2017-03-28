package com.yeedun.chinamobile.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yeedun.chinamobile.bo.MonthlyBillBO;
import com.yeedun.chinamobile.dao.MonthlyBillMapper;
import com.yeedun.chinamobile.model.MonthlyBill;

@Service
public class MonthlyBillBOImpl implements MonthlyBillBO {

	@Resource
	private MonthlyBillMapper MonthlyBillMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return MonthlyBillMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthlyBill record) {
		return MonthlyBillMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlyBill record) {
		return MonthlyBillMapper.insertSelective(record);
	}

	@Override
	public MonthlyBill selectByPrimaryKey(Long id) {
		return MonthlyBillMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlyBill record) {
		return MonthlyBillMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(MonthlyBill record) {
		return MonthlyBillMapper.updateByPrimaryKey(record);
	}

	@Override
	public MonthlyBill parseBill(String content, String isCurrentMonth, String phone, String queryMonth) {
		MonthlyBill mb = new MonthlyBill();
		mb.setPhone(phone);
		mb.setQueryMonth(queryMonth);
		mb.setQueryDate(new Date());
		mb.setIsCurrentMonth(isCurrentMonth);
		if ("0".equals(isCurrentMonth)) {//页面标签错误  直接取td
			Document doc = Jsoup.parse(content);
			Elements tds = doc.getElementsByTag("td");
			mb.setProductName(tds.get(2).text());
			mb.setBalance(new BigDecimal(tds.get(6).text().substring(0, tds.get(6).text().length()-2)));
			mb.setRealCost(new BigDecimal(tds.get(8).text().substring(0, tds.get(8).text().length()-2)));
			mb.setBaseCost(new BigDecimal(tds.get(12).text()));
			mb.setVoiceCost(new BigDecimal(tds.get(13).text()));
			mb.setSmsCost(new BigDecimal(tds.get(14).text()));
			mb.setNetworkCost(new BigDecimal(tds.get(15).text()));
			mb.setVasCost(new BigDecimal(tds.get(16).text()));
			mb.setRepresentCost(new BigDecimal(tds.get(17).text()));
			mb.setRepairCost(new BigDecimal(tds.get(18).text()));
			mb.setFavorableCost(new BigDecimal(tds.get(19).text()));
			mb.setThirdPartPay(new BigDecimal(tds.get(22).text()));
			mb.setOtherPay(new BigDecimal(tds.get(21).text()));
		} else {
			Document doc = Jsoup.parse(content);
			Elements tabs = doc.getElementsByTag("table");
			mb.setProductName(tabs.get(0).getElementsByTag("td").get(2).text());
			
			Elements tds = tabs.get(1).getElementsByTag("td");
			mb.setBalance(new BigDecimal(tds.get(0).text().substring(0, tds.get(0).text().length()-2)));
			mb.setRealCost(new BigDecimal(tds.get(2).text().substring(0, tds.get(2).text().length()-2)));
			
			tds = tabs.get(3).getElementsByTag("td");
			mb.setBaseCost(new BigDecimal(tds.get(2).text()));
			mb.setVoiceCost(new BigDecimal(tds.get(3).text()));
			mb.setSmsCost(new BigDecimal(tds.get(4).text()));
			mb.setNetworkCost(new BigDecimal(tds.get(5).text()));
			mb.setVasCost(new BigDecimal(tds.get(6).text()));
			mb.setRepresentCost(new BigDecimal(tds.get(7).text()));
			mb.setRepairCost(new BigDecimal(tds.get(8).text()));
			mb.setFavorableCost(new BigDecimal(tds.get(9).text()));
			mb.setThirdPartPay(new BigDecimal(tds.get(10).text()));
			mb.setOtherPay(new BigDecimal(tds.get(12).text()));
			
			Element tr = tabs.get(5).getElementsByTag("tr").get(2);
			tds = tr.getElementsByTag("td");
			mb.setRecharge(new BigDecimal(tds.get(2).text()));
			mb.setForeCapitalBack(new BigDecimal(tds.get(3).text()));
			mb.setForePresenterBack(new BigDecimal(tds.get(4).text()));
			mb.setOtherRecharge(new BigDecimal(tds.get(5).text()));
		}
		return mb;
	}

	@Override
	public int deleteBatch(Map<String, Object> map) {
		return 0;
	}

	@Override
	public int count(Map<String, Object> map) {
		return 0;
	}

	@Override
	public List<MonthlyBill> parseDetail(String htmlContent, String queryMonth,
			String phone, String isCurrent) {
		return null;
	}

	@Override
	public int addBatch(List<MonthlyBill> list) {
		return 0;
	}

	@Override
	public List<MonthlyBill> parseDetail(JSONObject json, String queryMonth,
			String phone, String isCurrent) {
		// TODO Auto-generated method stub
		return null;
	}
}
