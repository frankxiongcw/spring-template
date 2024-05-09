package com.template.api.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("SystemUserSaveDTO")
@Data
public class SystemUserSaveDTO {

    @ApiModelProperty(value = "账号")
    private String userCode;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String userName;

   
}
