package com.yeedun.chinamobile.vo;

public class LoginUser {
	
	private String phone;
	
	//移动服务密码
	private String fuwumima;
	
	//验证码
	private String validcode;
	
	//号码归属地
	private String locale;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFuwumima() {
		return fuwumima;
	}

	public void setFuwumima(String fuwumima) {
		this.fuwumima = fuwumima;
	}

	public String getValidcode() {
		return validcode;
	}

	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}

	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "LoginInfo [phone=" + phone + ", fuwumima=" + fuwumima
				+ ", validcode=" + validcode + "]";
	}
}
