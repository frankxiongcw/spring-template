package com.spring.template.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WeatherResponse implements Serializable {

    private String code;

    private WeatherVO now;

    @JsonIgnore
    private Object refer;
    @JsonIgnore
    private String fxLink;
    @JsonIgnore
    private Date updateTime;

}
