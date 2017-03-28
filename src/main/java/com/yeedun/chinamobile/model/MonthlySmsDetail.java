package com.yeedun.chinamobile.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlySmsDetail {
    private Long id;

    private String isCerrentMonth;

    private String phone;

    private String queryMonth;

    private Date queryDate;

    private Date smsStartTime;

    private String smsBusinessType;

    private String smsIsDriving;

    private String smsBusinessName;

    private String smsOppCallNum;

    private String smsOppArea;

    private String smsContentLength;

    private BigDecimal smsFee;

    private String smsFavorable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsCerrentMonth() {
        return isCerrentMonth;
    }

    public void setIsCerrentMonth(String isCerrentMonth) {
        this.isCerrentMonth = isCerrentMonth == null ? null : isCerrentMonth.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQueryMonth() {
        return queryMonth;
    }

    public void setQueryMonth(String queryMonth) {
        this.queryMonth = queryMonth == null ? null : queryMonth.trim();
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public Date getSmsStartTime() {
        return smsStartTime;
    }

    public void setSmsStartTime(Date smsStartTime) {
        this.smsStartTime = smsStartTime;
    }

    public String getSmsBusinessType() {
        return smsBusinessType;
    }

    public void setSmsBusinessType(String smsBusinessType) {
        this.smsBusinessType = smsBusinessType == null ? null : smsBusinessType.trim();
    }

    public String getSmsIsDriving() {
        return smsIsDriving;
    }

    public void setSmsIsDriving(String smsIsDriving) {
        this.smsIsDriving = smsIsDriving == null ? null : smsIsDriving.trim();
    }

    public String getSmsBusinessName() {
        return smsBusinessName;
    }

    public void setSmsBusinessName(String smsBusinessName) {
        this.smsBusinessName = smsBusinessName == null ? null : smsBusinessName.trim();
    }

    public String getSmsOppCallNum() {
        return smsOppCallNum;
    }

    public void setSmsOppCallNum(String smsOppCallNum) {
        this.smsOppCallNum = smsOppCallNum == null ? null : smsOppCallNum.trim();
    }

    public String getSmsOppArea() {
        return smsOppArea;
    }

    public void setSmsOppArea(String smsOppArea) {
        this.smsOppArea = smsOppArea == null ? null : smsOppArea.trim();
    }

    public String getSmsContentLength() {
        return smsContentLength;
    }

    public void setSmsContentLength(String smsContentLength) {
        this.smsContentLength = smsContentLength == null ? null : smsContentLength.trim();
    }

    public BigDecimal getSmsFee() {
        return smsFee;
    }

    public void setSmsFee(BigDecimal smsFee) {
        this.smsFee = smsFee;
    }

    public String getSmsFavorable() {
        return smsFavorable;
    }

    public void setSmsFavorable(String smsFavorable) {
        this.smsFavorable = smsFavorable == null ? null : smsFavorable.trim();
    }

	@Override
	public String toString() {
		return "MonthlySmsDetail [id=" + id + ", isCerrentMonth="
				+ isCerrentMonth + ", phone=" + phone + ", queryMonth="
				+ queryMonth + ", queryDate=" + queryDate + ", smsStartTime="
				+ smsStartTime + ", smsBusinessType=" + smsBusinessType
				+ ", smsIsDriving=" + smsIsDriving + ", smsBusinessName="
				+ smsBusinessName + ", smsOppCallNum=" + smsOppCallNum
				+ ", smsOppArea=" + smsOppArea + ", smsContentLength="
				+ smsContentLength + ", smsFee=" + smsFee + ", smsFavorable="
				+ smsFavorable + "]";
	}
}