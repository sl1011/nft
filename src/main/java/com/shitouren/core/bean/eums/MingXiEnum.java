package com.shitouren.core.bean.eums;

public enum MingXiEnum {

    收入酒滴(1),支出酒滴(-1),增加通行证(2),使用通行证(-2),增加贡献值(3);
    private int value;

    MingXiEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
