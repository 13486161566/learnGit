package com.yeedun.chinamobile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yeedun.chinamobile.service.CMCQService;
import com.yeedun.chinamobile.service.CMZJService;
import com.yeedun.chinamobile.vo.LoginUser;

@Controller
@RequestMapping("/chinamobile")
public class ChinaMobileCtl {
	
	public static Logger log = LoggerFactory.getLogger(ChinaMobileCtl.class);
	
	@Autowired
	private CMZJService cmzhSercive;
	
	@Autowired
	private CMCQService cmcqSercive;
	
	@RequestMapping("/index.do")  
	public ModelAndView index(Model model){
		ModelAndView view = new ModelAndView("login");
		return view;
	}
	
	@RequestMapping("/login.do")  
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser){
		String ret = null;
		if("zj".equals(loginUser.getLocale())){
			ret = cmzhSercive.login(request, response, loginUser, (DefaultHttpClient)request.getSession().getAttribute("httpClient"));
		}
		
		if("cq".equals(loginUser.getLocale())){
			try {
				ret = cmcqSercive.login(request, response, loginUser, (DefaultHttpClient)request.getSession().getAttribute("httpClient"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ModelAndView view = new ModelAndView("2ndvalidate");
		if(ret.equals("12")){
			return view;
		}
		log.info(ret);
		if(!ret.equals("1"))
			view = new ModelAndView("login");
		return view;
	}
	
	/**
	 * 获取登录验证图片
	 * @param request
	 * @param response
	 */
	@RequestMapping("/ImgDisp.do")  
	public void imgDisp(HttpServletRequest request, HttpServletResponse response){
		String sf = request.getParameter("sf");
		if("zj".equals(sf))
			cmzhSercive.getCode(request, response);
		
		if("cq".equals(sf)){
			cmcqSercive.getCode(request, response);
		}
			
	}
	
	/**
	 * 抓取用户历史详单信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/crawlInfo.do")
	public ModelAndView crawlInfo(HttpServletRequest request, HttpServletResponse response){
		String validateCode = request.getParameter("validateCode");
		if(StringUtils.isEmpty(validateCode))
			return new ModelAndView("2ndvalidate");
		
		cmzhSercive.crawlDetails(request, response, validateCode);
		return null;
	}
}
