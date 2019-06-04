package com.project.misae_project;

public class SharingData {
    private int img_;
    private String tv_name;
    private String tv_pm10;
    private String tv_pm25;

    public SharingData(){

    }
    public SharingData(int img_, String tv_name, String tv_pm10, String tv_pm25){
        this.img_ = img_;
        this.tv_name = tv_name;
        this.tv_pm10 = tv_pm10;
        this.tv_pm25 = tv_pm25;
    }

    public int getImg_() {return img_;}
    public void setImg_(int img_){this.img_ = img_;}
    public String getTv_name() {return tv_name;}
    public void setTv_name(String tv_name) {this.tv_name = tv_name;}
    public String getTv_pm10() {return tv_pm10;}
    public void setTv_pm10(String tv_pm10) {this.tv_pm10 = tv_pm10;}
    public String getTv_pm25() {return tv_pm25;}
    public void setTv_pm25(String tv_pm25) {this.tv_pm25 = tv_pm25;}
}
