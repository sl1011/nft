package com.shitouren.core.bean.eums;

/**
 * 会员级别枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum UserLevelEnum {

    黄精会员(1),钻石会员(2);

    private int value;

    UserLevelEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
