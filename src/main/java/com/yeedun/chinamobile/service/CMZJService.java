package com.yeedun.chinamobile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.yeedun.chinamobile.vo.LoginUser;

public interface CMZJService {

	public String login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, DefaultHttpClient httpClient);

	public void getCode(HttpServletRequest request, HttpServletResponse response);

	public void crawlDetails(HttpServletRequest request, HttpServletResponse response, String phoneValidate);
	
}