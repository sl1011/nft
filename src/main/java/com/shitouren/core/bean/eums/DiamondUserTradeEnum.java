package com.shitouren.core.bean.eums;

/**
 * 钻石会员A币交易枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
public enum DiamondUserTradeEnum {

    交易50枚(50,53,35),
    交易100枚(100,107,45),
    交易200枚(200,216,65),
    交易300枚(300,327,85),
    交易500枚(500,552,115),
    交易800枚(800,896,150),
    交易1000枚(1000,1156,180);

    private int buyCount;

    private int actualCount;

    private int releaseCount;

    DiamondUserTradeEnum(int buyCount,int actualCount,int releaseCount) {
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
