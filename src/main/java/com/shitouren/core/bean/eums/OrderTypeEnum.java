package com.shitouren.core.bean.eums;

/**
 * 订单类型相关枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum OrderTypeEnum {

    求购(0), 出售(1);

    private int value;

    OrderTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
