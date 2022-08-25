package com.shitouren.core.bean.eums;

/**
 * 用户锁仓枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum LockStatusEnum {

    未锁仓(0),锁仓(1);

    private int value;

    LockStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
