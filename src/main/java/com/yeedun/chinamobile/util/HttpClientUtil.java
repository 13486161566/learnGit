package com.yeedun.chinamobile.util;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	public static String Post(CloseableHttpClient httpclient, String url, List<NameValuePair> nvps) throws ClientProtocolException, IOException{
		return Post(httpclient, url, nvps, "utf-8");
	}
	
	public static String Post(CloseableHttpClient httpclient, String url, List<NameValuePair> nvps, String charset) throws ClientProtocolException, IOException{
		
		if(httpclient == null){
			httpclient = HttpClients.createDefault();
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		
		HttpEntity entity2 = response2.getEntity();
		String resultStr = EntityUtils.toString(entity2, charset);
		
		return resultStr;
	}
	
}