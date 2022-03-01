package com.unicode.app.Models;

public class BulkOrderModel {
    String sSize, mSize, lSize, xlSize, xxlSize, xxxlSize, mobileNo, orderTimestamp, fullName, orderId, dressSegment, dressType, gender;

    public BulkOrderModel(String orderId, String dressSegment, String gender, String dressType, String mobileNo, String fullName, String sSize, String mSize, String lSize, String xlSize, String xxlSize, String xxxlSize, String orderTimestamp) {
        this.sSize = sSize;
        this.mSize = mSize;
        this.lSize = lSize;
        this.xlSize = xlSize;
        this.xxlSize = xxlSize;
        this.xxxlSize = xxxlSize;
        this.orderTimestamp = orderTimestamp;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.dressSegment = dressSegment;
        this.dressType = dressType;
        this.orderId = orderId;
        this.gender = gender;
    }

    public String getsSize() {
        return sSize;
    }

    public void setsSize(String sSize) {
        this.sSize = sSize;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getlSize() {
        return lSize;
    }

    public void setlSize(String lSize) {
        this.lSize = lSize;
    }

    public String getXlSize() {
        return xlSize;
    }

    public void setXlSize(String xlSize) {
        this.xlSize = xlSize;
    }

    public String getXxlSize() {
        return xxlSize;
    }

    public void setXxlSize(String xxlSize) {
        this.xxlSize = xxlSize;
    }

    public String getXxxlSize() {
        return xxxlSize;
    }

    public void setXxxlSize(String xxxlSize) {
        this.xxxlSize = xxxlSize;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp12(String orderTimestamp12) {
        this.orderTimestamp = orderTimestamp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDressSegment() {
        return dressSegment;
    }

    public void setDressSegment(String dressSegment) {
        this.dressSegment = dressSegment;
    }

    public String getDressType() {
        return dressType;
    }

    public void setDressType(String dressType) {
        this.dressType = dressType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
