package com.unicode.app.Models;

public class PrecisedOrderHistoryModel {

    String orderId, gender, orderTimestamp, dressType, dressSegment, precisionDataJson;

    public String getOrderId() {
        return orderId;
    }

    public String getGender() {
        return gender;
    }

    public String getOrderTimestamp() {
        return orderTimestamp;
    }

    public String getDressType() {
        return dressType;
    }

    public String getPrecisionDataJson() {
        return precisionDataJson;
    }

    public String getDressSegment() {
        return dressSegment;
    }
}
