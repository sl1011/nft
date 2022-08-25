package com.shitouren.core.bean.eums;

/**
 * 用户会员激活枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum UserActivationEnum {

    未激活(0),已激活(1);

    private int value;

    UserActivationEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
