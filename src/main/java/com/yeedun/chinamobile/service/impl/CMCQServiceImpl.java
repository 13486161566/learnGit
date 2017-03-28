package com.yeedun.chinamobile.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.CookieManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.spi.LocaleServiceProvider;

import javax.annotation.Resource;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
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
import com.yeedun.chinamobile.service.CMCQService;
import com.yeedun.chinamobile.util.HttpsClientUtil;
import com.yeedun.chinamobile.vo.LoginUser;

/**
 * 中国移动服务类
 * @author admin
 */
@Service("cmcqSercive")
@SuppressWarnings("deprecation")
public class CMCQServiceImpl implements CMCQService {

	public static Logger log = LoggerFactory.getLogger(CMCQServiceImpl.class);

	@Override
	public String login(HttpServletRequest request,
			HttpServletResponse response, LoginUser loginUser,
			DefaultHttpClient httpClient) throws Exception {
		HttpPost post = null;
		String content = null;
		HttpGet get = null;
		//https://service.cq.10086.cn/ics?service=ajaxDirect/1/login/login/javascript/&pagename=login&eventname=SSOlogin&cond_REMEMBER_TAG=false&cond_LOGIN_TYPE=2&cond_SERIAL_NUMBER=18426496061&cond_USER_PASSWD=66D6C9EDB390443C409B630118298B84&cond_USER_PASSSMS=&cond_VALIDATE_CODE=c63c&ajaxSubmitType=post&ajax_randomcode=20161025175334530.34756885101239066
		post = new HttpPost("https://service.cq.10086.cn/ics?service=ajaxDirect/1/login/login/javascript/&pagename=login&eventname=SSOlogin&cond_REMEMBER_TAG=false&cond_LOGIN_TYPE=2&cond_SERIAL_NUMBER="+loginUser.getPhone()+"&cond_USER_PASSWD=66D6C9EDB390443C409B630118298B84&cond_USER_PASSSMS=&cond_VALIDATE_CODE="+loginUser.getValidcode()+"&ajaxSubmitType=post&ajax_randomcode="+new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.CHINA).format(new Date())+((Math.random()+"").substring(2)));
		
		post = new HttpPost("https://cq.ac.10086.cn/SSO/loginbox");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("service", "CHOQ"));
		nvps.add(new BasicNameValuePair("failUrlhttp", "http://service.cq.10086.cn/CHOQ/authentication/authentication_error.jsp"));
		nvps.add(new BasicNameValuePair("username", loginUser.getPhone()));
		nvps.add(new BasicNameValuePair("password", loginUser.getFuwumima()));
		nvps.add(new BasicNameValuePair("passwordType", "2"));
		nvps.add(new BasicNameValuePair("validateCode", loginUser.getValidcode()));
		nvps.add(new BasicNameValuePair("smsRandomCode", loginUser.getFuwumima()));
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		
		Document doc = Jsoup.parse(content);
		Element sso = doc.getElementById("sso");
		post = new HttpPost(sso.attr("action")+"?timeStamp="+System.currentTimeMillis());
		Elements inputs = doc.getElementsByTag("input");
		nvps = new ArrayList<NameValuePair>();
		for(Element input : inputs){
			nvps.add(new BasicNameValuePair(input.attr("name"), input.attr("value")));
		}
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		
		doc = Jsoup.parse(content);
		Element csf = doc.getElementById("caloginSearchForm");
		post = new HttpPost("http://service.cq.10086.cn"+csf.attr("action"));
		inputs = doc.getElementsByTag("input");
		nvps = new ArrayList<NameValuePair>();
		for(Element input : inputs){
			nvps.add(new BasicNameValuePair(input.attr("name"), input.attr("value")));
		}
		content = HttpsClientUtil.HttpPost(httpClient, post, nvps);
		log.info(content);
		
		
		//http://service.cq.10086.cn/
		get = new HttpGet("http://service.cq.10086.cn/ics?service=ajaxDirect/1/myMobile/myMobile/javascript/&pagename=myMobile&eventname=intigetnbyg&id=1&ajaxSubmitType=get&ajax_randomcode="+new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.CHINA).format(new Date())+((Math.random()+"").substring(2)));
		content = HttpsClientUtil.HttpGet(httpClient, get);
		log.info(content);
		
		getBaseInfo(httpClient);
		
		
		return null;
	}
	
	public void getBaseInfo(DefaultHttpClient httpClient) throws JDOMException, IOException{
		String url = "http://service.cq.10086.cn/ics?service=ajaxDirect/1/myMobile/myMobile/javascript/&pagename=myMobile&eventname=userInfo&cond_GOODS_ENAME=GRXX&cond_GOODS_NAME=%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF&cond_TRANS_TYPE=Q&cond_GOODS_ID=2015061800000665&ajaxSubmitType=get&ajax_randomcode="+new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.CHINA).format(new Date())+((Math.random()+"").substring(2));
		HttpPost post = new HttpPost(url);
		String content = HttpsClientUtil.HttpPost(httpClient, post, new ArrayList<NameValuePair>());
		SAXBuilder builder = new SAXBuilder(false);
		org.jdom.Document doc = builder.build(content);
		org.jdom.Element root = doc.getRootElement();
		String text = root.getChild("DATASETDATA").getText();
	}

	/**
	 * 获取图片验证码并输出
	 */
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		ServletOutputStream os = null;
		try {
			HttpsClientUtil.SecurityProcess(httpClient);
			if(request.getSession().getAttribute("httpClient")!=null)
				request.getSession().removeAttribute("httpClient");
			request.getSession().setAttribute("httpClient", httpClient);
			log.info(System.identityHashCode(httpClient)+"");
			byte[] b = getValidCode(httpClient, "https://cq.ac.10086.cn/SSO/img");
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

	@Override
	public void crawlDetails(HttpServletRequest request,
			HttpServletResponse response, String phoneValidate) {
		
	}
	
	public static void main(String[] args) {
		String cotent = "";
		System.out.println((Math.random()+"").substring(1));
		//1477029991957
		//1477030008582
	}
}
