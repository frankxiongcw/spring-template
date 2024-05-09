package com.template.api.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * create by zengli
 * 名片查看 请求参数
 */
@Data
public class BusinessCardDetailParam {
    //门店id
    private String shopId;
    //客户openId
    private String openId;
    //名片id
    private String id;
    //用户id
    private String userId;
    //导购员id
    private String shoppingGuideId;
    //图片file集合
    private List<MultipartFile> files;
    //图片base64集合
    private List<String> bases;
    //文件类型
    private String type;
    //文件格式
    private String dataType;
    //水印（1、去水印；2、不去水印）
    private Integer watermark;
    //缩放（1、缩放；2、不缩放）
    private Integer zoom;
    //裁切（1、裁切；2、不裁切）
    private Integer cutting;
    //比列
    private String billRow;
    //画质
    private String quality;
    //图片路径
    private String imgUrl;

}
