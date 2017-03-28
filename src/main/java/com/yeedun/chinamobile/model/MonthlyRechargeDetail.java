package com.yeedun.chinamobile.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlyRechargeDetail {
    private Long id;

    private String phone;

    private String queryMonth;

    private Date queryDate;

    private Date rechargeDate;

    private String rechargeWay;

    private BigDecimal capital;

    private BigDecimal presenter;

    private String favorable;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public String getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay == null ? null : rechargeWay.trim();
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getPresenter() {
        return presenter;
    }

    public void setPresenter(BigDecimal presenter) {
        this.presenter = presenter;
    }

    public String getFavorable() {
        return favorable;
    }

    public void setFavorable(String favorable) {
        this.favorable = favorable == null ? null : favorable.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}