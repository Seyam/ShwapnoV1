package com.example.shwapnov1.Model;

import com.google.gson.annotations.SerializedName;

public class SensorData {
    @SerializedName("div_id")
    private long DevId;
    @SerializedName("id")
    private  int Id;
    @SerializedName("pwr_cnsm")
    private  double power;
    @SerializedName("temp")
    private  double temperature;
    @SerializedName("time")
    private String dateTime;


    public long getDevId() {
        return DevId;
    }

    public int getId() {
        return Id;
    }

    public double getPower() {
        return power;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDateTime() {
        return dateTime;
    }
}
