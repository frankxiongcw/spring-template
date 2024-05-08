package com.template.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 清算明细
 * </p>
 *
 * @author xiong.canwei
 * @since 2024-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LiquidationDetail对象", description="清算明细")
public class LiquidationDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    private Long id;

    @ApiModelProperty(value = "清算批次号")
    private String batchNo;

    @ApiModelProperty(value = "内清分明细id")
    private Long clearingDetailId;

    @ApiModelProperty(value = "剩余可清算金额")
    private Long availLiqAmount;

    @ApiModelProperty(value = "通道商户号")
    private String payChannelMerNo;

    @ApiModelProperty(value = "支付通道")
    private String payChannel;

    @ApiModelProperty(value = "支付方式")
    private String payWay;

    @ApiModelProperty(value = "业务来源（大类）")
    private String bizSource;

    @ApiModelProperty(value = "订单来源（小类）")
    private String orderSource;

    @ApiModelProperty(value = "店铺号")
    private String shopNo;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "商户号")
    private String merNo;

    @ApiModelProperty(value = "商户名称")
    private String merName;

    @ApiModelProperty(value = "商场编号")
    private String marketNo;

    @ApiModelProperty(value = "商场名称")
    private String marketName;

    @ApiModelProperty(value = "公司编码")
    private String companyCode;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单类型")
    private String orderType;

    @ApiModelProperty(value = "订单流水号")
    private String orderPayNo;

    @ApiModelProperty(value = "交易类型（pay、refund）")
    private String tradeType;

    @ApiModelProperty(value = "支付流水号")
    private String paySeqNo;

    @ApiModelProperty(value = "交易参考号（三方支付订单号）")
    private String tradeReferenceNo;

    @ApiModelProperty(value = "清算类型（pay、refund、recover）")
    private String liqType;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime tradeTime;

    @ApiModelProperty(value = "订单金额")
    private Long orderAmount;

    @ApiModelProperty(value = "优惠金额")
    private Long discountAmount;

    @ApiModelProperty(value = "抵扣金额")
    private Long deductionAmount;

    @ApiModelProperty(value = "订单手续费（业务侧逻辑）")
    private Long orderFee;

    @ApiModelProperty(value = "订单手续费承担方（0: 付款方， 1: 收款方）")
    private Integer orderFeePayer;

    @ApiModelProperty(value = "交易金额")
    private Long tradeAmount;

    @ApiModelProperty(value = "清算金额")
    private Long liqAmount;

    @ApiModelProperty(value = "结算手续费（平台承担）")
    private Long liqFeePlatform;

    @ApiModelProperty(value = "结算手续费（商户承担）")
    private Long liqFeeMerchant;

    @ApiModelProperty(value = "手续费承担方(1：系统 2：商户）")
    private Integer liqFeePayer;

    @ApiModelProperty(value = "通道手续费（表示订单流水的整个手续费，不做为返款用）")
    private Long tradeFee;

    @ApiModelProperty(value = "通道手续费是否已结算，Y/N（针对不会自动收取手续费的通道，需要系统触发手续费扣取，此状态表示手续费是否已扣）")
    private String tradeFeeSettled;

    @ApiModelProperty(value = "其他金额字段01")
    private Long otherChannelAmount01;

    @ApiModelProperty(value = "佣金")
    private Long platformFee;

    @ApiModelProperty(value = "佣金")
    private Long commissionFee;

    @ApiModelProperty(value = "通道佣金")
    private Long channelCommissionFee;

    @ApiModelProperty(value = "退款金额（包含手续费）")
    private Long refundAmount;

    @ApiModelProperty(value = "退款手续费")
    private Long refundFee;

    @ApiModelProperty(value = "待结算日期")
    private LocalDate planRebateDate;

    @ApiModelProperty(value = "提现帐号标识")
    private String withdrawAccount;

    @ApiModelProperty(value = "银行（招行、人行等）")
    private String bank;

    @ApiModelProperty(value = "银行开户行")
    private String bankName;

    @ApiModelProperty(value = "银行行号")
    private String bankCode;

    @ApiModelProperty(value = "银行所在省份")
    private String bankProvice;

    @ApiModelProperty(value = "银行所在市")
    private String bankCity;

    @ApiModelProperty(value = "用户户名")
    private String bankUser;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "该店铺在通道内的入金虚拟帐号")
    private String virtualDepositAccount;

    @ApiModelProperty(value = "该店铺在通道内的出金虚拟帐号")
    private String virtualWithdrawAccount;

    @ApiModelProperty(value = "该店铺所在平台在通道内的虚拟帐号")
    private String platformVirtualAccount;

    @ApiModelProperty(value = "返款单号（尚未返款-1）")
    private Long rebateId;

    @ApiModelProperty(value = "返款状态（0:待结算，1:结算处理中，2:已结算，3:结算失败, 4: 取消结算）")
    private Integer rebateStatus;

    @ApiModelProperty(value = "生成返款单操作人编号")
    private String rebateGeneratorNo;

    @ApiModelProperty(value = "生成返款单操作人")
    private String rebateGeneratorName;

    @ApiModelProperty(value = "生成返款单时间")
    private LocalDateTime rebateGeneratedTime;

    @ApiModelProperty(value = "返款时间")
    private LocalDateTime rebateTime;

    @ApiModelProperty(value = "结算收据")
    private String rebateReceipt;

    @ApiModelProperty(value = "结算手续费是否已结算, Y/N")
    private String rebateFeeSettled;

    @ApiModelProperty(value = "出款服务费")
    private Long rebateServiceFee;

    @ApiModelProperty(value = "逻辑删除标志，0：未删除（默认值），1：已删除")
    private Integer deletionFlag;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime ctime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime mtime;

    @ApiModelProperty(value = "备忘、说明")
    private String memo;

    @ApiModelProperty(value = "返款分账状态（0:未生成，1:处理中，2:分账成功，3:分账失败）")
    private Integer rebateDivideStatus;

    @ApiModelProperty(value = "佣金分账状态（0:未生成，1:处理中，2:分账成功，3:分账失败）")
    private Integer brokerageDivideStatus;

    @ApiModelProperty(value = "结算商编")
    private String liqMarketNo;

    @ApiModelProperty(value = "阿里业务流水号")
    private String aliBusinessSerialNo;

    @ApiModelProperty(value = "返款分账流水号")
    private String rebateDivideSerialNo;

    @ApiModelProperty(value = "佣金分账流水号")
    private String brokerageDivideSerialNo;

    @ApiModelProperty(value = "佣金提现单号")
    private String brokerageWithdrawNo;

    @ApiModelProperty(value = "阿里同城站 新老数据区分展示")
    private Integer isNew;


}
