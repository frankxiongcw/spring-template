package com.spring.template.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/* "微信用户绑定手机号DTO
 *
 * @author roamer
 * @version v1.0
 * @date 2020/2/22 18:52"
*/
@Data
public class WxUserBindMobileDTO implements Serializable {

    private static final long serialVersionUID = 3117579839775160692L;

    @ApiModelProperty(value = "微信用户的openId")
    private String openId;

    @ApiModelProperty(value = "unionId 唯一标识")
    private String unionId;

    @ApiModelProperty(value = "手机号")
    private String mobile;


}