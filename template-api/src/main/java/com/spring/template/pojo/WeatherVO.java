package com.spring.template.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WeatherVO implements Serializable {

    private Date obsTime;
    private String temp;
    private String feelsLike;
    private String icon;
    private String text;
    private String wind360;
    private String windDir;
    private String windScale;
    private String windSpeed;
    private String humidity;
    private String precip;
    private String pressure;
    private String vis;
    private String cloud;
    private String dew;

    public String getIcon() {
        return "https://www.aorakipet.com/star-pick/api/file/"+icon+".png";
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
