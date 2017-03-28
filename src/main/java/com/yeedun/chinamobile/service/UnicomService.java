package com.yeedun.chinamobile.service;

import org.apache.http.impl.client.DefaultHttpClient;

import com.yeedun.chinamobile.vo.LoginUser;

public interface UnicomService {

	void login(LoginUser loginUser, DefaultHttpClient httpClient) throws Exception;
}