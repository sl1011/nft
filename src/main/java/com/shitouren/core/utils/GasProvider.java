package com.shitouren.core.utils;


import com.shitouren.core.config.ConfigCache;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

public class GasProvider implements ContractGasProvider {
    //默认值
    private String gasPrice = ConfigCache.get().getGasPrice();
    private String gasLimit = ConfigCache.get().getGasLimit();
    //自定义值
    private String funcGaslimit = ConfigCache.get().getFuncGasLimit();

    @Override
    public BigInteger getGasPrice(String s) {
        return CommonUtils.string2BigInteger(this.gasPrice);
    }

    @Override
    public BigInteger getGasPrice() {
        return CommonUtils.string2BigInteger(this.gasPrice);
    }

    @Override
    public BigInteger getGasLimit(String s) {
        if (!funcGaslimit.equals("0")) {
            ConfigCache.get().getMap().put(s, funcGaslimit);
            ConfigCache.get().setFuncGasLimit("0");
            return CommonUtils.string2BigInteger(ConfigCache.get().getMap().get(s));
        } else {
            BigInteger defaultLimit = CommonUtils.string2BigInteger(ConfigCache.get().getMap().get(s));
            if (defaultLimit.intValue() == 0) {
                return new BigInteger(this.gasLimit);
            }
            return defaultLimit;
        }
    }

    @Override
    public BigInteger getGasLimit() {
        return CommonUtils.string2BigInteger(this.gasLimit);
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public void setGasLimit(String gasLimit) {
        this.gasLimit = gasLimit;
    }
}
