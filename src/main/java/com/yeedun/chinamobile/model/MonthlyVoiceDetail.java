package com.yeedun.chinamobile.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlyVoiceDetail {
    private Long id;

    private String isCerrentMonth;

    private String phone;

    private String queryMonth;

    private Date queryDate;

    private Date voiceStartTime;

    private String voiceHoidingTime;

    private String voiceCallArea;

    private String voiceType;

    private String voiceOppCallNum;

    private String voiceOppNumType;

    private String voiceOppArea;

    private String voiceBusinessName;

    private BigDecimal voiceCallBaseFee;

    private BigDecimal voiceLongCallFee;

    private BigDecimal voiceCallInfoFee;

    private String voiceFavorable;

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

    public Date getVoiceStartTime() {
        return voiceStartTime;
    }

    public void setVoiceStartTime(Date voiceStartTime) {
        this.voiceStartTime = voiceStartTime;
    }

    public String getVoiceHoidingTime() {
        return voiceHoidingTime;
    }

    public void setVoiceHoidingTime(String voiceHoidingTime) {
        this.voiceHoidingTime = voiceHoidingTime == null ? null : voiceHoidingTime.trim();
    }

    public String getVoiceCallArea() {
        return voiceCallArea;
    }

    public void setVoiceCallArea(String voiceCallArea) {
        this.voiceCallArea = voiceCallArea == null ? null : voiceCallArea.trim();
    }

    public String getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(String voiceType) {
        this.voiceType = voiceType == null ? null : voiceType.trim();
    }

    public String getVoiceOppCallNum() {
        return voiceOppCallNum;
    }

    public void setVoiceOppCallNum(String voiceOppCallNum) {
        this.voiceOppCallNum = voiceOppCallNum == null ? null : voiceOppCallNum.trim();
    }

    public String getVoiceOppNumType() {
        return voiceOppNumType;
    }

    public void setVoiceOppNumType(String voiceOppNumType) {
        this.voiceOppNumType = voiceOppNumType == null ? null : voiceOppNumType.trim();
    }

    public String getVoiceOppArea() {
        return voiceOppArea;
    }

    public void setVoiceOppArea(String voiceOppArea) {
        this.voiceOppArea = voiceOppArea == null ? null : voiceOppArea.trim();
    }

    public String getVoiceBusinessName() {
        return voiceBusinessName;
    }

    public void setVoiceBusinessName(String voiceBusinessName) {
        this.voiceBusinessName = voiceBusinessName == null ? null : voiceBusinessName.trim();
    }

    public BigDecimal getVoiceCallBaseFee() {
        return voiceCallBaseFee;
    }

    public void setVoiceCallBaseFee(BigDecimal voiceCallBaseFee) {
        this.voiceCallBaseFee = voiceCallBaseFee;
    }

    public BigDecimal getVoiceLongCallFee() {
        return voiceLongCallFee;
    }

    public void setVoiceLongCallFee(BigDecimal voiceLongCallFee) {
        this.voiceLongCallFee = voiceLongCallFee;
    }

    public BigDecimal getVoiceCallInfoFee() {
        return voiceCallInfoFee;
    }

    public void setVoiceCallInfoFee(BigDecimal voiceCallInfoFee) {
        this.voiceCallInfoFee = voiceCallInfoFee;
    }

    public String getVoiceFavorable() {
        return voiceFavorable;
    }

    public void setVoiceFavorable(String voiceFavorable) {
        this.voiceFavorable = voiceFavorable == null ? null : voiceFavorable.trim();
    }

	@Override
	public String toString() {
		return "MonthlyVoiceDetail [id=" + id + ", isCerrentMonth="
				+ isCerrentMonth + ", phone=" + phone + ", queryMonth="
				+ queryMonth + ", queryDate=" + queryDate + ", voiceStartTime="
				+ voiceStartTime + ", voiceHoidingTime=" + voiceHoidingTime
				+ ", voiceCallArea=" + voiceCallArea + ", voiceType="
				+ voiceType + ", voiceOppCallNum=" + voiceOppCallNum
				+ ", voiceOppNumType=" + voiceOppNumType + ", voiceOppArea="
				+ voiceOppArea + ", voiceBusinessName=" + voiceBusinessName
				+ ", voiceCallBaseFee=" + voiceCallBaseFee
				+ ", voiceLongCallFee=" + voiceLongCallFee
				+ ", voiceCallInfoFee=" + voiceCallInfoFee
				+ ", voiceFavorable=" + voiceFavorable + "]";
	}
}