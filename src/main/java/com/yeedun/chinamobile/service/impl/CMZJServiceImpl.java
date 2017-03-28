package com.yeedun.chinamobile.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yeedun.chinamobile.bo.MonthlyBillBO;
import com.yeedun.chinamobile.bo.MonthlyGprsDetailBO;
import com.yeedun.chinamobile.bo.MonthlyRechargeDetailBO;
import com.yeedun.chinamobile.bo.MonthlySmsDetailBO;
import com.yeedun.chinamobile.bo.MonthlyVasDetailBO;
import com.yeedun.chinamobile.bo.MonthlyVoiceDetailBO;
import com.yeedun.chinamobile.bo.UserBO;
import com.yeedun.chinamobile.common.BO;
import com.yeedun.chinamobile.dao.CitiesMapper;
import com.yeedun.chinamobile.dao.ProvincesMapper;
import com.yeedun.chinamobile.model.Cities;
import com.yeedun.chinamobile.model.MonthlyBill;
import com.yeedun.chinamobile.model.MonthlyGprsDetail;
import com.yeedun.chinamobile.model.MonthlyRechargeDetail;
import com.yeedun.chinamobile.model.MonthlySmsDetail;
import com.yeedun.chinamobile.model.MonthlyVasDetail;
import com.yeedun.chinamobile.model.MonthlyVoiceDetail;
import com.yeedun.chinamobile.model.Provinces;
import com.yeedun.chinamobile.model.User;
import com.yeedun.chinamobile.service.CMZJService;
import com.yeedun.chinamobile.util.HttpsClientUtil;
import com.yeedun.chinamobile.vo.LoginUser;

/**
 * 中国移动服务类
 * @author admin
 */
@Service("cmzjService")
@SuppressWarnings("deprecation")
public class CMZJServiceImpl implements CMZJService {

	public static Logger log = LoggerFactory.getLogger(CMZJServiceImpl.class);
	
	public static String bid = "BC5CC0A69BC10482E044001635842132";
	
	public static String baseURL = "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do";
	
	public static String billURL = "http://service.zj.10086.cn/yw/bill/billDetail.do";
	
	public static final String DATEFORMAT_MM_YYYY = "MM-YYYY";
	
	/**
	 * 语音
	 */
	public static final int MENUID_13009 = 13009;
	
	/**
	 * 历史月账单
	 */
	public static final int MENUID_13003 = 13003;
	
	/**
	 * 当前月账单
	 */
	public static final int MENUID_13104 = 13104;
	
	/**
	 * 短信
	 */
	public static final int MENUID_13011 = 13011;
	
	/**
	 * 增值业务
	 */
	public static final int MENUID_13282 = 13282;
	
	/**
	 * 上网流量
	 */
	public static final int MENUID_13015 = 13015;
	
	/**
	 * 语音
	 */
	public static final int LISTTYPE_1 = 1;
	
	/**
	 * 上网流量
	 */
	public static final int LISTTYPE_8 = 8;
	
	/**
	 * 短信
	 */
	public static final int LISTTYPE_2 = 2;
	
	/**
	 * 增值业务
	 */
	public static final int LISTTYPE_25 = 25;

	@Resource
	private UserBO userBO;
	
	@Resource
	private ProvincesMapper provincesMapper;
	
	@Resource
	private CitiesMapper citiesMapper;
	
	@Resource
	private MonthlyVoiceDetailBO monthlyVoiceDetailBO;
	
	@Resource
	private MonthlySmsDetailBO monthlySmsDetailBO;
	
	@Resource
	private MonthlyGprsDetailBO monthlyGprsDetailBO;
	
	@Resource
	private MonthlyVasDetailBO monthlyVasDetailBO;
	
	@Resource
	private MonthlyBillBO monthlyBillBO;
	
	@Resource
	private MonthlyRechargeDetailBO monthlyRechargeDetailBO;
	
	public String login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, DefaultHttpClient httpClient){
		HttpPost post = new HttpPost("https://zj.ac.10086.cn/loginbox");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("SMSpwdType", "0"));
		nvps.add(new BasicNameValuePair("billId", loginUser.getPhone()));
		nvps.add(new BasicNameValuePair("continue", "/my/login/loginSuccess.do"));
		nvps.add(new BasicNameValuePair("failurl", "https://zj.ac.10086.cn/login"));
		nvps.add(new BasicNameValuePair("mima", "fuwumima"));
		nvps.add(new BasicNameValuePair("passwd", loginUser.getFuwumima()));
		nvps.add(new BasicNameValuePair("pwdType", "2"));
		nvps.add(new BasicNameValuePair("service", "my"));
		nvps.add(new BasicNameValuePair("style", "1"));
		nvps.add(new BasicNameValuePair("validCode", loginUser.getValidcode()));		
		String content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.debug(content);
		
		Document doc = Jsoup.parse(content);
		post = new HttpPost(doc.getElementsByTag("form").get(0).attr("action"));
		nvps = new ArrayList<NameValuePair>();
		Elements elts= doc.getElementsByTag("input");
		for(Element elt : elts){
			nvps.add(new BasicNameValuePair(elt.attr("name"), elt.attr("value")));
		}
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.debug(content);
		
		doc = Jsoup.parse(content);
		nvps = new ArrayList<NameValuePair>();
		elts= doc.getElementsByTag("input");
		for(Element elt : elts){
			nvps.add(new BasicNameValuePair(elt.attr("name"), elt.attr("value")));
		}
		post = new HttpPost("http://www.zj.10086.cn/my/UnifiedLoginClientServlet");
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.debug(content);
		
		HttpGet get = new HttpGet("http://www.zj.10086.cn/my/login/loginSuccess.do");
		content = HttpsClientUtil.HttpGet(httpClient, get);
		log.debug(content);
		
		get = new HttpGet("http://www.zj.10086.cn/my/index.do");
		content = HttpsClientUtil.HttpGet(httpClient, get);
		log.debug(content);
		
		get = new HttpGet("http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009");
		content = HttpsClientUtil.HttpGet(httpClient, get);
		doc = Jsoup.parse(content);
		nvps = new ArrayList<NameValuePair>();
		elts= doc.getElementsByTag("input");
		for(Element elt : elts){
			nvps.add(new BasicNameValuePair(elt.attr("name"), elt.attr("value")));
		}
		post = new HttpPost("http://www.zj.10086.cn/my/UnifiedLoginServerServlet?AISSO_LOGIN=true");
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.debug(content);
		
		doc = Jsoup.parse(content);
		nvps = new ArrayList<NameValuePair>();
		elts= doc.getElementsByTag("input");
		for(Element elt : elts){
			nvps.add(new BasicNameValuePair(elt.attr("name"), elt.attr("value")));
		}
		post = new HttpPost("http://service.zj.10086.cn/UnifiedLoginClientServlet");
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.debug(content);
		
		//获取用户基本信息
		User user = getBaseInfo(loginUser, httpClient);
		request.getSession().setAttribute("user", user);
		
		//获取用户每月账单和充值明细
		getBill(httpClient, loginUser.getPhone());
		
		get = new HttpGet("http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009");
		content = HttpsClientUtil.HttpGet(httpClient, get);
		log.debug(content);
		
		post = new HttpPost("http://service.zj.10086.cn/yw/detail/secondPassCheck.do");
		content = HttpsClientUtil.HttpPost(httpClient, post, new ArrayList<NameValuePair>());
		log.debug(content);
		return content;
	}
	
	/**
	 * 获取用户基本信息
	 * @param loginInfo
	 * @param httpClient
	 * @return
	 */
	public User getBaseInfo(LoginUser loginUser, DefaultHttpClient httpClient){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("phone", loginUser.getPhone());
		User user = userBO.get(map);
		if(StringUtils.isEmpty(user)){
			user = new User();
			user.setAddDate(new Date());
			user.setPhone(loginUser.getPhone());
			user.setOperators("1");
			HashMap<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("province", "浙江省");
			Provinces pvs = provincesMapper.get(queryMap);
			user.setProvince(pvs.getProvinceid());
			queryMap.clear();
			queryMap.put("city", "杭州市");
			Cities ct = citiesMapper.get(queryMap);
			user.setCity(ct.getCityid());
		}
		user.setPassword(loginUser.getFuwumima());
		user.setQueryDate(new Date());
		String basicContent = crawlBasicInfo(httpClient);
		parseBasicInfo(basicContent, user);

		//获取用户积分http://www.zj.10086.cn/my/findUserInfos.do?AISSO_LOGIN=true
		parseIntegral(crawlIntegral(httpClient), user);
		if(StringUtils.isEmpty(userBO.get(map))){
			userBO.insert(user);
		}else{
			userBO.updateByPrimaryKeySelective(user);
		}
		return user;
	}
	
	/**
	 * 获取用户每月账单和充值明细
	 * @param httpClient
	 */
	public void getBill(DefaultHttpClient httpClient, String phone){
		String content = "";
		List<MonthlyRechargeDetail> list = null;
		for(int i = 0; i>-6; i--){
			content = config(i, billURL+"?menuId="+(i==0?MENUID_13104:MENUID_13003)+"&bid=&month=", phone, monthlyBillBO, httpClient);
			if (content != null) {
				MonthlyBill mb = monthlyBillBO.parseBill(content, (i==0?"0":"1"), phone, DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), DATEFORMAT_MM_YYYY));
				monthlyBillBO.insert(mb);
				if (i != 0) {
					list = monthlyRechargeDetailBO.parseDetail(content, DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), DATEFORMAT_MM_YYYY), phone, "0");
					monthlyRechargeDetailBO.addBatch(list);
				}
			}
		}
	}
	
	/**
	 * 解析数据前删除表中为当前月份的数据，已有数据则跳过解析
	 * @param loop
	 * @param url
	 * @param phone
	 * @param bo
	 * @param httpClient
	 * @return
	 */
	private String config(int loop, String url, String phone, BO<?> bo, DefaultHttpClient httpClient){
		String queryMonth = DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), loop), DATEFORMAT_MM_YYYY);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("phone", phone);
		queryMap.put("queryMonth", queryMonth);
		if(loop==0){
			bo.deleteBatch(queryMap);
		}
		if (loop != 0) {
			queryMap.put("count_column", "id");
			int size = bo.count(queryMap);
			if(size>0)return null;
		}
		HttpGet get = new HttpGet(url+(loop==0?"0":queryMonth));
		return HttpsClientUtil.HttpGet(httpClient, get);
	}
	
	public void UnifiedLogin(DefaultHttpClient httpClient){
		HttpPost post = new HttpPost("http://www.zj.10086.cn/my/UnifiedLoginServerServlet?AISSO_LOGIN=true");
		String content = HttpsClientUtil.HttpPost(httpClient, post, new ArrayList<NameValuePair>());
		Document doc = Jsoup.parse(content);
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Elements elts = doc.getElementsByTag("input");
		for(Element elt : elts){
			nvps.add(new BasicNameValuePair(elt.attr("name"), elt.attr("value")));
		}
		post = new HttpPost("http://service.zj.10086.cn/UnifiedLoginClientServlet");
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.debug(content);
	}
	
	/**
	 * 解析用户积分
	 * @param content
	 * @param u
	 */
	private void parseIntegral(String content, User u){
		Document doc = Jsoup.parse(content);
		Elements elts = doc.getElementsByAttributeValue("href", "http://jf.10086.cn/");
		String integral = elts.get(0).text().substring(1, elts.get(0).text().length()-1);
		u.setCurrentIntegral(Integer.valueOf(integral));
	}
	
	/**
	 * 爬取积分页面
	 * @param httpClient
	 * @return
	 */
	private String crawlIntegral(DefaultHttpClient	httpClient){
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("AISSO_LOGIN", "true"));
		HttpPost post = new HttpPost("http://www.zj.10086.cn/my/findUserInfos.do");
		return HttpsClientUtil.HttpPost(httpClient, post, nvps);
	}
	
	/**
	 * 获取图片验证码并输出
	 */
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		DefaultHttpClient httpClient = null;
		ServletOutputStream os = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpsClientUtil.SecurityProcess(httpClient);
			request.getSession().setAttribute("httpClient", httpClient);
			byte[] b = getValidCode(httpClient, "https://zj.ac.10086.cn/ImgDisp?tmp="+new Date().getTime());
			os = response.getOutputStream();
			os.write(b, 0, b.length);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.flushBuffer();
				if(os != null)os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public byte[] getValidCode(DefaultHttpClient httpClient, String url) throws KeyManagementException, NoSuchAlgorithmException{
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			return EntityUtils.toByteArray(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void parseHtml(String fileName) throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\admin\\Desktop\\html\\"+fileName)));
		try {
			String line = "";
			String text = "";
			while((line = reader.readLine())!=null)
			text += line;
			//parseBasicInfo(text, new User());
			List<MonthlySmsDetail> list = monthlySmsDetailBO.parseDetail(text, "10-2016", "13486161566", "0");
			for(MonthlySmsDetail a : list){
				System.out.println(a.toString());
			}
			ApplicationContext ac = null;
			ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
			monthlySmsDetailBO = (MonthlySmsDetailBO) ac.getBean("monthlySmsDetailMapper");
			monthlySmsDetailBO.addBatch(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析用户基本信息
	 * @param content
	 * @param user
	 * @return
	 */
	public void parseBasicInfo(String content, User user){
		Document doc = Jsoup.parse(content);
		Elements tabs = doc.getElementsByTag("table");
		Elements tds = tabs.get(0).getElementsByTag("td");
		user.setRealName(tds.get(0).text());
		user.setPhone(tds.get(1).text());
		user.setProductName(tds.get(2).text());
		tds = tabs.get(1).getElementsByTag("td");
		user.setCurrentBalance(new BigDecimal(tds.get(0).text().substring(0, tds.get(0).text().length()-2)));
		user.setCurrentExpenses(new BigDecimal(tds.get(1).text().substring(0, tds.get(1).text().length()-2)));
	}
	
	/**
	 * 保存页面到本地
	 * @param con
	 * @param fileName
	 */
	public void saveLocal(String con, String fileName){
		BufferedWriter bw = null;
		try {
			File f = new File("C:/Users/admin/Desktop/html/"+fileName);
			if(!f.exists())f.createNewFile();
			bw = new BufferedWriter(new FileWriter(f));
			bw.write(con);
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * 爬取用户业务详情
	 */
	public void crawlDetails(HttpServletRequest request, HttpServletResponse response, String validateCode) {
		request.getSession().setAttribute("validateCode", validateCode);
		DefaultHttpClient httpclient = (DefaultHttpClient)request.getSession().getAttribute("httpClient");
		
		//二次验证
		HttpPost post = new HttpPost("http://service.zj.10086.cn/yw/detail/secondPassCheck.do");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("bid", bid));
		nvps.add(new BasicNameValuePair("validateCode", validateCode));
		HttpsClientUtil.HttpPost(httpclient, post, nvps);
		
		String phone = ((User)request.getSession().getAttribute("user")).getPhone();
		
		///////////////////////语音详单操作开始////////////////////////////////
		for(int i=0; i>-6; i--){
			String queryMonth = DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), DATEFORMAT_MM_YYYY);
			String htmlContent = config_(i, baseURL+"?bid=&menuId="+MENUID_13009+"&listtype="+LISTTYPE_1+"&month="+queryMonth, phone, monthlyVoiceDetailBO, httpclient);
			List<MonthlyVoiceDetail> voicelist = monthlyVoiceDetailBO.parseDetail(htmlContent, queryMonth, phone, (i==0?"0":"1"));
			monthlyVoiceDetailBO.addBatch(voicelist);
		}
		///////////////////////语音详单操作结束////////////////////////////////
		
		//////////////////////短信详单操作开始//////////////////////////////////
		for(int i=0; i>-6; i--){
			String queryMonth = DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), DATEFORMAT_MM_YYYY);
			String htmlContent = config_(i, baseURL+"?bid=&menuId="+MENUID_13011+"&listtype="+LISTTYPE_2+"&month="+queryMonth, phone, monthlySmsDetailBO, httpclient);
			List<MonthlySmsDetail> smslist = monthlySmsDetailBO.parseDetail(htmlContent, queryMonth, phone, (i==0?"0":"1"));
			monthlySmsDetailBO.addBatch(smslist);
		}
		//////////////////////短信详单操作结束//////////////////////////////////
		
		//////////////////////上网流量详单操作开始//////////////////////////////////
		for(int i=0; i>-6; i--){
			String queryMonth = DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), DATEFORMAT_MM_YYYY);
			String htmlContent = config_(i, baseURL+"?bid=&menuId="+MENUID_13015+"&listtype="+LISTTYPE_8+"&month="+queryMonth, phone, monthlyGprsDetailBO, httpclient);
			List<MonthlyGprsDetail> smslist = monthlyGprsDetailBO.parseDetail(htmlContent, queryMonth, phone, (i==0?"0":"1"));
			monthlyGprsDetailBO.addBatch(smslist);
		}
		//////////////////////上网流量详单操作结束//////////////////////////////////
		
		//////////////////////增值业务详单操作开始//////////////////////////////////
		for(int i=0; i>-6; i--){			
		String queryMonth = DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), i), DATEFORMAT_MM_YYYY);
		String htmlContent = config_(i, baseURL+"?bid=&menuId="+MENUID_13282+"&listtype="+LISTTYPE_25+"&month="+queryMonth, phone, monthlyVasDetailBO, httpclient);
		List<MonthlyVasDetail> smslist = monthlyVasDetailBO.parseDetail(htmlContent, queryMonth, phone, (i==0?"0":"1"));
		monthlyVasDetailBO.addBatch(smslist);
		}
		//////////////////////增值业务详单操作结束//////////////////////////////////
	}
	
	private String config_(int loop, String url, String phone, BO<?> bo, DefaultHttpClient httpClient){
		String queryMonth = DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), loop), DATEFORMAT_MM_YYYY);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("phone", phone);
		queryMap.put("queryMonth", queryMonth);
		if(loop==0){
			bo.deleteBatch(queryMap);
		}
		if (loop != 0) {
			queryMap.put("count_column", "id");
			int size = bo.count(queryMap);
			if(size>0)return null;
		}
		HttpGet get = new HttpGet(url);
		return HttpsClientUtil.HttpGet(httpClient, get);
	}

	/**
	 * 爬取用户基本信息
	 * @param httpclient
	 * @return
	 */
	private String crawlBasicInfo(DefaultHttpClient httpclient){
		HttpGet get = new HttpGet("http://service.zj.10086.cn/yw/bill/billDetail.do?menuId=13104&month=0");
		String content = HttpsClientUtil.HttpGet(httpclient, get);
		return content;
	}
}
