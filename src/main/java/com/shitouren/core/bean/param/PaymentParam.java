package com.shitouren.core.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PaymentParam {

    @ApiModelProperty("二维码")
    private String qrCode;

    /**
     *
     */
    @ApiModelProperty("支付宝账号")
    private String payId;
    /**
     *
     */
    @ApiModelProperty("1:银行卡/2:支付宝/3:USDT")
    private Integer type;

    @ApiModelProperty("姓名")
    private String name;
}
