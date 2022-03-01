package com.unicode.app.Models;

public class UserModel {

    String fullName, mobileNo, password, deliveryAddress;

    public UserModel() {
    }

    public UserModel(String fullName, String mobileNo, String password, String deliveryAddress) {
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
