package com.shitouren.core.bean.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeParam {

    /**
     * 求购订单 已有buyer
     * price 为挂单时的价格
     */

    private BigDecimal count;

    /**
     * 1:微信 2:支付宝
     */
    private Integer payment;

    private BigDecimal price;
    private String tradePassword;

}
