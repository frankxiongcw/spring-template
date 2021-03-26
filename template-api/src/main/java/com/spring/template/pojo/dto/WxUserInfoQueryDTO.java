package com.spring.template.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("微信用户查询参数DTO")
@Data
public class WxUserInfoQueryDTO {

    private static final long serialVersionUID = -3262748426691941538L;


    @ApiModelProperty(value = "pageNo")
    @NotNull(message = "pageNo不可为空")
    private int pageNo;

    @ApiModelProperty(value = "pageSize")
    @NotNull(message = "pageSize不可为空")
    private int pageSize;

}