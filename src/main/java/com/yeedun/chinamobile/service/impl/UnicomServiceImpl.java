package com.yeedun.chinamobile.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeedun.chinamobile.bo.MonthlyGprsDetailBO;
import com.yeedun.chinamobile.bo.MonthlySmsDetailBO;
import com.yeedun.chinamobile.bo.MonthlyVoiceDetailBO;
import com.yeedun.chinamobile.bo.UserBO;
import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.model.MonthlyGprsDetail;
import com.yeedun.chinamobile.model.MonthlySmsDetail;
import com.yeedun.chinamobile.model.MonthlyVoiceDetail;
import com.yeedun.chinamobile.model.User;
import com.yeedun.chinamobile.service.CMZJService;
import com.yeedun.chinamobile.service.UnicomService;
import com.yeedun.chinamobile.util.Dateutils;
import com.yeedun.chinamobile.util.HttpsClientUtil;
import com.yeedun.chinamobile.vo.LoginUser;

/**
 * 中国移动服务类
 * @author admin
 */
@Service("UnicomService")
@SuppressWarnings("deprecation")
public class UnicomServiceImpl implements UnicomService {

	public static Logger log = LoggerFactory.getLogger(UnicomServiceImpl.class);
	
	public static final SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat SDF_MM_YYYY = new SimpleDateFormat("MM-yyyy");
	
	@Resource
	private MonthlyVoiceDetailBO monthlyVoiceDetailBO;
	
	@Resource
	private MonthlySmsDetailBO monthlySmsDetailBO;
	
	@Resource
	private MonthlyGprsDetailBO monthlyGprsDetailBO;
	
	@Resource
	private UserBO userBO;
	
	@Override
	public void login(LoginUser loginUser, DefaultHttpClient httpClient) throws Exception {
		String phone = loginUser.getPhone();
		HttpGet get = new HttpGet("https://uac.10010.com/portal/Service/MallLogin?req_time="+System.currentTimeMillis()+"&redirectURL=http%3A%2F%2Fwww.10010.com&userName="+phone+"&password="+loginUser.getFuwumima()+"&pwdType=01&productType=01&redirectType=03&rememberMe=1&_="+(System.currentTimeMillis()-50000L));
		HttpsClientUtil.HttpGet(httpClient, get);
		HttpPost post = new HttpPost("http://iservice.10010.com/e3/static/check/checklogin/?_="+System.currentTimeMillis());
		HttpsClientUtil.HttpPost(httpClient, post, new ArrayList<NameValuePair>());
		
		//获取用户信息
		getBaseInfo(httpClient, phone);
		
		//获取语音详情
//		post = new HttpPost("http://iservice.10010.com/e3/static/query/callDetail?_="+System.currentTimeMillis()+"&menuid=000100030001");
//		List<?> list = getDetail(httpClient, post, monthlyVoiceDetailBO, loginUser.getPhone());
//		monthlyVoiceDetailBO.addBatch((List<MonthlyVoiceDetail>)list);
//		getVoiceDetail(httpClient, loginUser.getPhone());
		
		//获取短信详情
//		post= new HttpPost("http://iservice.10010.com/e3/static/query/sms?_="+System.currentTimeMillis()+"&menuid=000100030002");
//		list = getDetail(httpClient, post, monthlySmsDetailBO, loginUser.getPhone());
//		monthlySmsDetailBO.addBatch((List<MonthlySmsDetail>)list);
//		getSmsDetail(httpClient, loginUser.getPhone());
		
//		getGprsDetail(httpClient, loginUser.getPhone());
	}
	
	public void getBaseInfo(DefaultHttpClient httpClient, String phone){
		HttpPost post = new HttpPost("http://iservice.10010.com/e3/static/query/headerView");
		String content = HttpsClientUtil.HttpPost(httpClient, post, new ArrayList<NameValuePair>());
		JSONObject json = JSON.parseObject(content);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		User user = userBO.get(map);
		if(user == null){
			user = new User();
			user.setAddDate(new Date());
			user.setOperators("2");
			user.setPhone(phone);
			JSONObject userInfo = json.getJSONObject("userinfo");
			user.setRealName(userInfo.getString("custname"));
		}
		user.setQueryDate(new Date());
		JSONObject result = json.getJSONObject("result");
		user.setCurrentBalance(new BigDecimal(result.getString("account")));
		user.setCurrentExpenses(new BigDecimal(result.getString("realfee")));
		user.setProductName(result.getString("nowpackage"));
		if(StringUtils.isEmpty(userBO.get(map))){
			userBO.insert(user);
		}else{
			userBO.updateByPrimaryKeySelective(user);
		}
		//积分详情http://iservice.10010.com/e3/static/query/scoreFourgResult?_=1476941609236
	}
	
//	public JSONObject config(DefaultHttpClient httpClient, HttpPost post, int loop, String phone) throws Exception{
//		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//		String dateStr = SDF_YYYY_MM_DD.format(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), loop));
//		nvps.add(new BasicNameValuePair("beginDate", Dateutils.getFirstDay(dateStr)));
//		nvps.add(new BasicNameValuePair("endDate", Dateutils.getLastDay(dateStr)));
//		nvps.add(new BasicNameValuePair("pageNo", "1"));
//		nvps.add(new BasicNameValuePair("pageSize", "10000000"));
//		String queryMonth = SDF_MM_YYYY.format(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), loop));
//		Map<String, Object> queryMap = new HashMap<String, Object>();
//		queryMap.put("phone", phone);
//		queryMap.put("queryMonth", queryMonth);
//		if(loop==0){
//			monthlySmsDetailBO.deleteBatch(queryMap);
//		}
//		if (1!=0) {
//			queryMap.put("count_column", "id");
//			if(monthlySmsDetailBO.count(queryMap)>0)return null;
//		}
//		String content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
//		JSONObject json = JSON.parseObject(content);
//		if("false".equals(json.getString("isSuccess")))return null;
//		return json;
//	}
	
	/**
	 * 获取短信详情
	 * @param httpClient
	 * @param phone
	 * @throws Exception
	 */
	public void getSmsDetail(DefaultHttpClient httpClient, String phone) throws Exception{
		HttpPost post= new HttpPost("http://iservice.10010.com/e3/static/query/sms?_="+System.currentTimeMillis()+"&menuid=000100030002");
		List<MonthlySmsDetail> list = null;
		for(int i = 0; i>-6; i--){
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("begindate", Dateutils.getFirstDay(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), "yyyyMMdd")));
			nvps.add(new BasicNameValuePair("enddate", Dateutils.getLastDay(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), "yyyyMMdd")));
			nvps.add(new BasicNameValuePair("pageNo", "1"));
			nvps.add(new BasicNameValuePair("pageSize", "10000000"));
			String queryMonth = SDF_MM_YYYY.format(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i));
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("phone", phone);
			queryMap.put("queryMonth", queryMonth);
			if(i==0){
				monthlySmsDetailBO.deleteBatch(queryMap);
			}
			if (1!=0) {
				queryMap.put("count_column", "id");
				if(monthlySmsDetailBO.count(queryMap)>0)continue;
			}
			String content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
			JSONObject json = JSON.parseObject(content);
			if("false".equals(json.getString("isSuccess")))continue;
			list = monthlySmsDetailBO.parseDetail(json, queryMonth, phone, (i==0?"0":"1"));
			monthlySmsDetailBO.addBatch(list);
		}
	}
	
	/**
	 * 获取语音详情
	 * @param httpClient
	 * @param phone
	 * @throws Exception
	 */
	public void getVoiceDetail(DefaultHttpClient httpClient, String phone) throws Exception{
		HttpPost post = new HttpPost("http://iservice.10010.com/e3/static/query/callDetail?_="+System.currentTimeMillis()+"&menuid=000100030001");
		List<MonthlyVoiceDetail> list = null;
		for(int i = 0; i>-6; i--){
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("beginDate", Dateutils.getFirstDay(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), "yyyy-MM-dd")));
			nvps.add(new BasicNameValuePair("endDate", Dateutils.getLastDay(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), "yyyy-MM-dd")));
			nvps.add(new BasicNameValuePair("pageNo", "1"));
			nvps.add(new BasicNameValuePair("pageSize", "10000000"));
			String queryMonth = SDF_MM_YYYY.format(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i));
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("phone", phone);
			queryMap.put("queryMonth", queryMonth);
			if(i==0){
				monthlyVoiceDetailBO.deleteBatch(queryMap);
			}
			if (1!=0) {
				queryMap.put("count_column", "id");
				if(monthlyVoiceDetailBO.count(queryMap)>0)continue;
			}
			String content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
			JSONObject json = JSON.parseObject(content);
			if("false".equals(json.getString("isSuccess")))continue;
			list = monthlyVoiceDetailBO.parseDetail(json, queryMonth, phone, (i==0?"0":"1"));
			monthlyVoiceDetailBO.addBatch(list);
		}
	}
	
	/**
	 * 获取上网详情
	 * @param httpClient
	 * @param phone
	 * @throws Exception
	 */
	public void getGprsDetail(DefaultHttpClient httpClient, String phone) throws Exception{
		HttpPost post = new HttpPost("http://iservice.10010.com/e3/static/query/callFlow?_="+System.currentTimeMillis()+"&menuid=000100030004");
		List<MonthlyGprsDetail> list = null;
		
		outfor:for(int i = 0; i>-6; i--){
			String queryMonth = SDF_MM_YYYY.format(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i));
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("phone", phone);
			queryMap.put("queryMonth", queryMonth);
			if(i==0){
				monthlyGprsDetailBO.deleteBatch(queryMap);
			}
			if (1!=0) {
				queryMap.put("count_column", "id");
				if(monthlyGprsDetailBO.count(queryMap)>0)continue;
			}
			int totalpage = 0;
			int times = 0;
			do {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("beginDate", Dateutils.getFirstDay(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), "yyyy-MM-dd")));
				nvps.add(new BasicNameValuePair("endDate", Dateutils.getLastDay(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), "yyyy-MM-dd")));
				nvps.add(new BasicNameValuePair("pageSize", "100"));
				nvps.add(new BasicNameValuePair("pageNo", ++times+""));
				String content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
				JSONObject json = JSON.parseObject(content);
				if(json == null || (json.containsKey("issuccess")&&!json.getBooleanValue("issuccess")))continue outfor;
				totalpage = json.getIntValue("totalpage");
				list = monthlyGprsDetailBO.parseDetail(json, queryMonth, phone, (i==0?"0":"1"));
				monthlyGprsDetailBO.addBatch(list);
			} while (times<totalpage);
		}
	}
	public static void main(String[] args) throws Exception {
	}
}
