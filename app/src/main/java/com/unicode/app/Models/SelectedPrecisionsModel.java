package com.unicode.app.Models;

public class SelectedPrecisionsModel {
    private String precisionValue, precisionName;

    public String getPrecisionValue() {
        return precisionValue;
    }

    public void setPrecisionValue(String precisionValue) {
        this.precisionValue = precisionValue;
    }

    public String getPrecisionName(){
        return precisionName;
    }

    public void setPrecisionName(String precisionName){
        this.precisionName = precisionName;
    }

    @Override
    public String toString() {
        return "SelectedPrecisionsModel{" +
                "precisionValue='" + precisionValue + '\'' +
                ", precisionName='" + precisionName + '\'' +
                '}';
    }

}
