package com.spring.template.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("WxUserVO")
@Data
public class WxUserVO implements Serializable {

    private static final long serialVersionUID = -3118945261354680500L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "微信用户的唯一标识")
    private String openId;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户的性别，1:男性，2:女，0:未知")
    private Integer sex;

    @ApiModelProperty(value = "用户个人资料填写的省份")
    private String province;

    @ApiModelProperty(value = "普通用户个人资料填写的城市")
    private String city;

    @ApiModelProperty(value = "国家，如中国为CN")
    private String country;

    @ApiModelProperty(value = "用户头像")
    private String headImgUrl;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "首次登陆时间")
    private Date createTime;




}