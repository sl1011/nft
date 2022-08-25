package com.shitouren.core.utils;

import org.web3j.protocol.core.DefaultBlockParameter;

import java.math.BigInteger;

public class CommonUtils {
    private CommonUtils() {
    }

    /**
     * String转换为BigInteger
     *
     * @param val String类型
     * @return BigInteger类型
     */
    public static BigInteger string2BigInteger(String val) {
        boolean numeric00 = CommonUtils.isNumeric00(val);
        if (numeric00) {
            return BigInteger.valueOf(Long.valueOf(val));
        } else {
            throw new NumberFormatException();
        }
    }

    /**
     * 判断字符串是否由数字组成
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNumeric00(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 块高格式转换
     *
     * @param num 区块高度
     * @return DefaultBlockParameter
     */
    public static DefaultBlockParameter getDefaultBlockParamter(String num) {
        if (isNumeric00(num)) {
            return DefaultBlockParameter.valueOf(BigInteger.valueOf(Long.parseLong(num)));
        } else {
            throw new NumberFormatException();
        }
    }

}
