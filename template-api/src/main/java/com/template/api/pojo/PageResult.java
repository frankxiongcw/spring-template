package com.template.api.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页输出结果基类
 *
 * @author frank.xiong
 * @date 2020/3/23 10:48
 */
@Data
@ApiModel("分页输出结果基类")
public class PageResult<E> implements Serializable {

    private static final long serialVersionUID = -146111865024913407L;

    @ApiModelProperty("第几页")
    private int pageNo;

    @ApiModelProperty("每页多少数据")
    private int pageSize;

    @ApiModelProperty("总页数")
    private int totalPages;

    @ApiModelProperty("总记录数")
    private long totalRecords;

    @ApiModelProperty("返回记录列表")
    private List<E> records;

    public List<E> getRecords() {
        return records == null ? new ArrayList<>(0) : records;
    }
}