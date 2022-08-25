package com.shitouren.core.bean.eums;

/**
 * 订单状态相关枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum OrderStatusEnum {

    未支付(0),支付中(1), 支付成功(2),支付失败(3);

    private int value;

    OrderStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
