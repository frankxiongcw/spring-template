package com.template.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel("登录用户对象")
public class User implements Serializable {

    private static final long serialVersionUID = -5877897780537115242L;

    // 登录端及session

    @ApiModelProperty("sessionId")
    private String sessionId;

    // user基础信息
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "用户编码")
    private String userCode;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "员工编号")
    private String userNo;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "性别, 0:未知;1:男;2:女;")
    private Integer gender;
    @ApiModelProperty(value = "账号类型, 1:admin,2:普通用户")
    private Integer userType;

    @ApiModelProperty(value = "是否需要短信验证")
    private Boolean smsCheck=false;
    @ApiModelProperty(value = "发送短信token")
    private String smsToken;


    @ApiModelProperty("用户授权项目列表")
    private List<Long> projects;
    @ApiModelProperty("用户授权一级模块列表")
    private List<Long> modules;
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;

    // 小程序相关
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "unionId")
    private String unionId;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "用户IP")
    private String userIp;

    public User() {
    }

    public User(String mobile, Boolean smsCheck, String smsToken) {
        this.mobile = mobile;
        this.smsCheck = smsCheck;
        this.smsToken = smsToken;
    }

}
