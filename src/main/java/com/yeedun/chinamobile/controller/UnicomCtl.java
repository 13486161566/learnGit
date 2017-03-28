package com.yeedun.chinamobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yeedun.chinamobile.service.UnicomService;
import com.yeedun.chinamobile.vo.LoginUser;

@Controller
@RequestMapping("/unicom")
public class UnicomCtl {
	
	private Logger log = LoggerFactory.getLogger(UnicomCtl.class);
	
	@Resource
	private UnicomService unicomService;

	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser){
		System.out.println(loginUser.toString());
		try {
			unicomService.login(loginUser, new DefaultHttpClient());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
