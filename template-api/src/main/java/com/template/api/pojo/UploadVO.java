package com.template.api.pojo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadVO implements Serializable {

    private static final long serialVersionUID = -7423323719701919513L;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件url")
    private String fileUrl;

    @ApiModelProperty("文件类型")
    private String fileType;
}