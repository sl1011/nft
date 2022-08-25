package com.shitouren.core.bean.eums;

/**
 * 激活码有效枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum ExpireStatusEnum {

    生效(0),过期(1);

    private int value;

    ExpireStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
