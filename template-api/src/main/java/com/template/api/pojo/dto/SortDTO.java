package com.template.api.pojo.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("排序实体")
public class SortDTO implements Serializable {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "Id不可为空")
    private Long id;

    @ApiModelProperty(value = "操作类型（top:置于顶层，up:上移一层,down:下移一层,bottom:置于底层）")
    @NotNull(message = "操作类型不可为空")
    private String operateType;
}
