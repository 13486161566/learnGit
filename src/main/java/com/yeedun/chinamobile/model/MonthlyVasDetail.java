package com.yeedun.chinamobile.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlyVasDetail {
    private Long id;

    private String isCerrentMonth;

    private String phone;

    private String queryMonth;

    private Date queryDate;

    private Date vasStartTime;

    private String vasCostDate;

    private String vasProductName;

    private String vasBusinessCode;

    private String vasBusinessName;

    private String vasBusinessType;

    private String vasCostType;

    private BigDecimal vasFee;

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

    public Date getVasStartTime() {
        return vasStartTime;
    }

    public void setVasStartTime(Date vasStartTime) {
        this.vasStartTime = vasStartTime;
    }

    public String getVasCostDate() {
        return vasCostDate;
    }

    public void setVasCostDate(String vasCostDate) {
        this.vasCostDate = vasCostDate;
    }

    public String getVasProductName() {
        return vasProductName;
    }

    public void setVasProductName(String vasProductName) {
        this.vasProductName = vasProductName == null ? null : vasProductName.trim();
    }

    public String getVasBusinessCode() {
        return vasBusinessCode;
    }

    public void setVasBusinessCode(String vasBusinessCode) {
        this.vasBusinessCode = vasBusinessCode == null ? null : vasBusinessCode.trim();
    }

    public String getVasBusinessName() {
        return vasBusinessName;
    }

    public void setVasBusinessName(String vasBusinessName) {
        this.vasBusinessName = vasBusinessName == null ? null : vasBusinessName.trim();
    }

    public String getVasBusinessType() {
        return vasBusinessType;
    }

    public void setVasBusinessType(String vasBusinessType) {
        this.vasBusinessType = vasBusinessType == null ? null : vasBusinessType.trim();
    }

    public String getVasCostType() {
        return vasCostType;
    }

    public void setVasCostType(String vasCostType) {
        this.vasCostType = vasCostType == null ? null : vasCostType.trim();
    }

    public BigDecimal getVasFee() {
        return vasFee;
    }

    public void setVasFee(BigDecimal vasFee) {
        this.vasFee = vasFee;
    }
}