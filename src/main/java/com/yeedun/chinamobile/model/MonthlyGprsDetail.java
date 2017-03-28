package com.yeedun.chinamobile.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlyGprsDetail {
    private Long id;

    private String isCerrentMonth;

    private String phone;

    private String queryMonth;

    private Date queryDate;

    private Date gprsStartTime;

    private String gprsBusinessType;

    private String gprsBusinessName;

    private String gprsNetworkIdentify;

    private String gprsUseArea;

    private String gprsContentLength;

    private String gprsCostTime;

    private BigDecimal gprsFee;

    private String gprsFavorable;

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
        this.queryMonth = queryMonth;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public Date getGprsStartTime() {
        return gprsStartTime;
    }

    public void setGprsStartTime(Date gprsStartTime) {
        this.gprsStartTime = gprsStartTime;
    }

    public String getGprsBusinessType() {
        return gprsBusinessType;
    }

    public void setGprsBusinessType(String gprsBusinessType) {
        this.gprsBusinessType = gprsBusinessType == null ? null : gprsBusinessType.trim();
    }

    public String getGprsBusinessName() {
        return gprsBusinessName;
    }

    public void setGprsBusinessName(String gprsBusinessName) {
        this.gprsBusinessName = gprsBusinessName == null ? null : gprsBusinessName.trim();
    }

    public String getGprsNetworkIdentify() {
        return gprsNetworkIdentify;
    }

    public void setGprsNetworkIdentify(String gprsNetworkIdentify) {
        this.gprsNetworkIdentify = gprsNetworkIdentify == null ? null : gprsNetworkIdentify.trim();
    }

    public String getGprsUseArea() {
        return gprsUseArea;
    }

    public void setGprsUseArea(String gprsUseArea) {
        this.gprsUseArea = gprsUseArea == null ? null : gprsUseArea.trim();
    }

    public String getGprsContentLength() {
        return gprsContentLength;
    }

    public void setGprsContentLength(String gprsContentLength) {
        this.gprsContentLength = gprsContentLength == null ? null : gprsContentLength.trim();
    }

    public String getGprsCostTime() {
        return gprsCostTime;
    }

    public void setGprsCostTime(String gprsCostTime) {
        this.gprsCostTime = gprsCostTime == null ? null : gprsCostTime.trim();
    }

    public BigDecimal getGprsFee() {
        return gprsFee;
    }

    public void setGprsFee(BigDecimal gprsFee) {
        this.gprsFee = gprsFee;
    }

    public String getGprsFavorable() {
        return gprsFavorable;
    }

    public void setGprsFavorable(String gprsFavorable) {
        this.gprsFavorable = gprsFavorable == null ? null : gprsFavorable.trim();
    }
}