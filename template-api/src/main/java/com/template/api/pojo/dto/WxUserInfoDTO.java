package com.template.api.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户保存DTO
 *
 * @author roamer
 * @version v1.0
 * @date 2020/2/20 18:52
 */
@Data
public class WxUserInfoDTO implements Serializable {
    private static final long serialVersionUID = -6835904987329963134L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * unionId 唯一标识
     */
    private String unionId;

    /**
     * 会员用户编号
     */
    private Integer userCode;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户的性别，1:男性，2:女，0:未知
     */
    private Integer sex;

    /**
     * 用户个人资料填写的省份
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 微信头像原始Url
     */
    private String avatarUrl;

    /**
     * 用户头像
     */
    private String headImgUrl;

    /**
     * 手机号
     */
    private String mobile;






}