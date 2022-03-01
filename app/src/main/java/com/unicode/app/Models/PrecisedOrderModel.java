package com.unicode.app.Models;

public class PrecisedOrderModel {

    private String precisionDataJson, mobileNo, fullName, orderTimestamp, dressSegment, gender, dressType, orderId;

    public PrecisedOrderModel(String orderId, String dressSegment, String gender, String dressType, String mobileNo, String fullName, String orderTimestamp, String precisionDataJson) {
        this.precisionDataJson = precisionDataJson;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.orderTimestamp = orderTimestamp;
        this.gender = gender;
        this.dressSegment = dressSegment;
        this.dressType = dressType;
        this.orderId = orderId;
    }

    public String getPrecisionDataJson() {
        return precisionDataJson;
    }

    public void setPrecisionDataJson(String precisionDataJson) {
        this.precisionDataJson = precisionDataJson;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDressSegment() {
        return dressSegment;
    }

    public void setDressSegment(String dressSegment) {
        this.dressSegment = dressSegment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDressType() {
        return dressType;
    }

    public void setDressType(String dressType) {
        this.dressType = dressType;
    }

    public String getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(String orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
