package com.unicode.app.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefer {

    SharedPreferences profilePreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_MOBILENO = "mobileNo";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATA = "PrefData";
    public static final String KEY_DELIVERY_ADDRESS = "deliveryAddress";
    public static final String KEY_FLAT_HOLDING_ROAD_NO = "flatNo";
    public static final String KEY_POST_OFFICE = "postOffice";
    public static final String KEY_POLICE_STATION = "policeStation";
    public static final String KEY_DISTRICT = "district";

    public SharedPrefer(Context ctx) {
        context = ctx;
        profilePreferences = context.getSharedPreferences(KEY_DATA, Context.MODE_PRIVATE);
        editor = profilePreferences.edit();
    }

    public String getFullName (){
        return profilePreferences.getString(KEY_FULLNAME, null);
    }

    public void setFullName (String fullName){
        editor.putString(KEY_FULLNAME, fullName).commit();
    }

    public String getMobileNo(){
        return profilePreferences.getString(KEY_MOBILENO,null);
    }

    public void setMobileNo(String mobileNo){
        editor.putString(KEY_MOBILENO, mobileNo).commit();
    }

    public String getPassword(){
        return profilePreferences.getString(KEY_PASSWORD,null);
    }

    public void setPassword(String password){
        editor.putString(KEY_PASSWORD, password).commit();
    }

    public String getFlatHoldingRoadNo(){
        return profilePreferences.getString(KEY_FLAT_HOLDING_ROAD_NO,null);
    }

    public void setFlatHoldingRoadNo(String flatNo){
        editor.putString(KEY_FLAT_HOLDING_ROAD_NO, flatNo).commit();
    }

    public String getPostOffice(){
        return profilePreferences.getString(KEY_POST_OFFICE,null);
    }

    public void setPostOffice(String postOffice){
        editor.putString(KEY_POST_OFFICE, postOffice).commit();
    }

    public String getPoliceStation(){
        return profilePreferences.getString(KEY_POLICE_STATION,null);
    }

    public void setPoliceStation(String policeStation){
        editor.putString(KEY_POLICE_STATION, policeStation).commit();
    }

    public String getDistrict(){
        return profilePreferences.getString(KEY_DISTRICT,null);
    }

    public void setDistrict(String district){
        editor.putString(KEY_DISTRICT, district).commit();
    }

    public String getDeliveryAddress (){
        return profilePreferences.getString(KEY_DELIVERY_ADDRESS, null);
    }

    public void setDeliveryAddress (String deliveryAddress){

        editor.putString(KEY_DELIVERY_ADDRESS, deliveryAddress).commit();
    }

    public void clear(){
        SharedPreferences sharedpreferences = context.getSharedPreferences(KEY_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}
