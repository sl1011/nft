package com.shitouren.core.bean.eums;

public enum  TradeEnum {

    挂单中(0),未付款(1),待收款(2),已完成(3),撤单(-1),投诉中(-2),投诉成功(-3);

    private int value;

    TradeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
