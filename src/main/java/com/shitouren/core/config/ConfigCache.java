package com.shitouren.core.config;

import org.web3j.utils.Strings;

import java.util.concurrent.ConcurrentHashMap;

public class ConfigCache {
    private ConfigCache() {
    }

    private static final String DDC_SDK_CACHE_KEY = "ddc_sdk_config";

    private static final ConcurrentHashMap<String, ConfigInfo> MAP = new ConcurrentHashMap<>();

    public static void initCache(String gasPrice, String gasLimit, String ddc721Address, String ddc1155Address, String authorityLogicAddress, String chargeLogicAddress) {
        ConfigInfo configInfo = new ConfigInfo();

        if (!Strings.isEmpty(ddc721Address)) {
            configInfo.setDdc721Address(ddc721Address);
        }
        if (!Strings.isEmpty(ddc1155Address)) {
            configInfo.setDdc1155Address(ddc1155Address);
        }
        if (!Strings.isEmpty(authorityLogicAddress)) {
            configInfo.setAuthorityLogicAddress(authorityLogicAddress);
        }
        if (!Strings.isEmpty(chargeLogicAddress)) {
            configInfo.setChargeLogicAddress(chargeLogicAddress);
        }
        if (!Strings.isEmpty(gasPrice)) {
            configInfo.setGasPrice(gasPrice);
        }
        if (!Strings.isEmpty(gasLimit)) {
            configInfo.setGasLimit(gasLimit);
        }
        MAP.put(DDC_SDK_CACHE_KEY, configInfo);

    }

    public static final ConfigInfo get() {
        return MAP.get(DDC_SDK_CACHE_KEY);
    }

}
