package com.template.api.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("User")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5454966330523535136L;
    @ApiModelProperty("userCode")
    private String userCode;
    private String userName;
    @ApiModelProperty("密码")
    private String password;
    private String openId;
    private String token;
}
