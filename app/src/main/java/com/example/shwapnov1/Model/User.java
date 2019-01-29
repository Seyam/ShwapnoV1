package com.example.shwapnov1.Model;


public class User {

    private String device_no;
    private String temperature;


//    private String response;
//    private  Integer id;

    public User(String device_no, String temperature) {
        this.device_no = device_no;
        this.temperature = temperature;
    }

    public String getDevice_no() {
        return device_no;
    }

    public String getTemperature() {
        return temperature;
    }


}
