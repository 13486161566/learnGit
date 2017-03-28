package com.yeedun.chinamobile.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpsClientUtil {

	/**
	 * SSL部分的处理
	 * 
	 * @param httpClient
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void SecurityProcess(DefaultHttpClient httpClient) throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager easyTrustManager = new X509TrustManager() {
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] x509Certificates,
					String s) throws java.security.cert.CertificateException {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] x509Certificates,
					String s) throws java.security.cert.CertificateException {
			}

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[0];
			}
		};
		SSLContext sslcontext = SSLContext.getInstance("TLSv1.2");

		sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
		SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
		Scheme sch = new Scheme("https", 443, sf);
		httpClient.getConnectionManager().getSchemeRegistry().register(sch);
	}
	
	/**
	 * 返回CloseableHttpClient
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static CloseableHttpClient SecurityProcess() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager easyTrustManager = new X509TrustManager() {
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] x509Certificates,
					String s) throws java.security.cert.CertificateException {
			}
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] x509Certificates,
					String s) throws java.security.cert.CertificateException {
			}
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[0];
			}
		};
		SSLContext sslcontext = SSLContext.getInstance("TLSv1.2");
		sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
		SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslcontext);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sf).build();

	//	创建ConnectionManager，添加Connection配置信息
		RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).setSocketTimeout(1000*60*30).setConnectTimeout(1000*60*30).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		return HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();
	}
	
	public static CloseableHttpClient buildSSLCloseableHttpClient() throws Exception {
	    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				return true;
			}
	    }).build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2" }, null, 
	            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	    return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	/**
	 * https post请求
	 * @param httpClient
	 * @param httpPost
	 * @param url
	 * @param params
	 * @return
	 * @throws RequestException
	 */
	public static String HttpPost(DefaultHttpClient httpClient, HttpPost httpPost, List<NameValuePair> params){
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 处理登录
	 * 
	 * @param httpClient
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static HttpEntity HttpPost(DefaultHttpClient httpClient, String url, List<NameValuePair> params)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
		HttpPost httppost = new HttpPost(url);

		httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
		httppost.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = httpClient.execute(httppost);
		HttpEntity entity = response.getEntity();

		return entity;
	}
	
	/**
	 * https get请求
	 * @param httpClient
	 * @param httpGet
	 * @return
	 * @throws RequestException
	 */
	public static String HttpGet(DefaultHttpClient httpClient, HttpGet httpGet) {
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 处理登录
	 * 
	 * @param httpClient
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static CloseableHttpResponse  HttpPostForHttpResponse(CloseableHttpClient httpClient, String url, List<NameValuePair> params)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
		HttpPost httppost = new HttpPost(url);

		httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
		httppost.setEntity(new UrlEncodedFormEntity(params));

		return httpClient.execute(httppost);
	}
	
	public static Map<String, String> getCookieMap(Map<String, String> cookieMap, DefaultHttpClient httpClient){
		CookieStore cook = httpClient.getCookieStore();
		List<Cookie> list = cook.getCookies();
		for(Cookie c : list){
			if(cookieMap.containsKey(c.getName()))continue;
			cookieMap.put(c.getName(), c.getValue());
		}
		return cookieMap;
	}
}
