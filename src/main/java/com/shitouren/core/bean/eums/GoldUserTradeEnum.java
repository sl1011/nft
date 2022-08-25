package com.shitouren.core.bean.eums;

/**
 * 黄金会员A币交易枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum GoldUserTradeEnum {

    交易50枚(50,53,30),
    交易100枚(100,107,40),
    交易200枚(200,216,60),
    交易300枚(300,327,70),
    交易500枚(500,552,95),
    交易800枚(800,896,130),
    交易1000枚(1000,1156,150);

    private int buyCount;

    private int actualCount;

    private int releaseCount;

    GoldUserTradeEnum(int buyCount, int actualCount, int releaseCount) {
        this.buyCount = buyCount;
        this.actualCount = actualCount;
        this.releaseCount = releaseCount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }
    public Integer getActualCount() {
        return actualCount;
    }
    public Integer getReleaseCount() {
        return releaseCount;
    }
}
