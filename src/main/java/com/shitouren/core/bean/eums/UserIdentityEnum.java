package com.shitouren.core.bean.eums;

/**
 * 用户实名认证枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum UserIdentityEnum {

    未认证(0),已认证(1);

    private int value;

    UserIdentityEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
