package com.template.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyImageVO implements Serializable {

    private static final long serialVersionUID = 2002244658696447914L;

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "图片验证码base64字符串")
    private String base64str;


}