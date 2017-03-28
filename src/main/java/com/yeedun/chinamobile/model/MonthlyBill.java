package com.yeedun.chinamobile.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlyBill {
    private Long id;

    private String phone;

    private String queryMonth;

    private Date queryDate;

    private String isCurrentMonth;

    private String productName;

    private BigDecimal balance;

    private BigDecimal realCost;

    private BigDecimal baseCost;

    private BigDecimal voiceCost;

    private BigDecimal smsCost;

    private BigDecimal networkCost;

    private BigDecimal vasCost;

    private BigDecimal representCost;

    private BigDecimal repairCost;

    private BigDecimal favorableCost;

    private BigDecimal thirdPartPay;

    private BigDecimal otherPay;

    private BigDecimal recharge;

    private BigDecimal foreCapitalBack;

    private BigDecimal forePresenterBack;

    private BigDecimal otherRecharge;

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

    public String getIsCurrentMonth() {
        return isCurrentMonth;
    }

    public void setIsCurrentMonth(String isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth == null ? null : isCurrentMonth.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getRealCost() {
        return realCost;
    }

    public void setRealCost(BigDecimal realCost) {
        this.realCost = realCost;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    public BigDecimal getVoiceCost() {
        return voiceCost;
    }

    public void setVoiceCost(BigDecimal voiceCost) {
        this.voiceCost = voiceCost;
    }

    public BigDecimal getSmsCost() {
        return smsCost;
    }

    public void setSmsCost(BigDecimal smsCost) {
        this.smsCost = smsCost;
    }

    public BigDecimal getNetworkCost() {
        return networkCost;
    }

    public void setNetworkCost(BigDecimal networkCost) {
        this.networkCost = networkCost;
    }

    public BigDecimal getVasCost() {
        return vasCost;
    }

    public void setVasCost(BigDecimal vasCost) {
        this.vasCost = vasCost;
    }

    public BigDecimal getRepresentCost() {
        return representCost;
    }

    public void setRepresentCost(BigDecimal representCost) {
        this.representCost = representCost;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public BigDecimal getFavorableCost() {
        return favorableCost;
    }

    public void setFavorableCost(BigDecimal favorableCost) {
        this.favorableCost = favorableCost;
    }

    public BigDecimal getThirdPartPay() {
        return thirdPartPay;
    }

    public void setThirdPartPay(BigDecimal thirdPartPay) {
        this.thirdPartPay = thirdPartPay;
    }

    public BigDecimal getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(BigDecimal otherPay) {
        this.otherPay = otherPay;
    }

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getForeCapitalBack() {
        return foreCapitalBack;
    }

    public void setForeCapitalBack(BigDecimal foreCapitalBack) {
        this.foreCapitalBack = foreCapitalBack;
    }

    public BigDecimal getForePresenterBack() {
        return forePresenterBack;
    }

    public void setForePresenterBack(BigDecimal forePresenterBack) {
        this.forePresenterBack = forePresenterBack;
    }

    public BigDecimal getOtherRecharge() {
        return otherRecharge;
    }

    public void setOtherRecharge(BigDecimal otherRecharge) {
        this.otherRecharge = otherRecharge;
    }

	@Override
	public String toString() {
		return "MonthlyBill [id=" + id + ", phone=" + phone + ", queryMonth="
				+ queryMonth + ", queryDate=" + queryDate + ", isCurrentMonth="
				+ isCurrentMonth + ", productName=" + productName
				+ ", balance=" + balance + ", realCost=" + realCost
				+ ", baseCost=" + baseCost + ", voiceCost=" + voiceCost
				+ ", smsCost=" + smsCost + ", networkCost=" + networkCost
				+ ", vasCost=" + vasCost + ", representCost=" + representCost
				+ ", repairCost=" + repairCost + ", favorableCost="
				+ favorableCost + ", thirdPartPay=" + thirdPartPay
				+ ", otherPay=" + otherPay + ", recharge=" + recharge
				+ ", foreCapitalBack=" + foreCapitalBack
				+ ", forePresenterBack=" + forePresenterBack
				+ ", otherRecharge=" + otherRecharge + "]";
	}
}