package com.shitouren.core.utils;

import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class HexUtils {
    private HexUtils() {
    }

    /**
     * 验证传入参数 是否为 有效的4位Byte的哈希格式字符串
     *
     * @param str 验证字符串
     * @return 返回验证结果， true或者false。
     */
    public static boolean isValid4ByteHash(String str) {
        String strNoPrefix = Numeric.cleanHexPrefix(str);
        boolean valid = strNoPrefix.length() == 8;
        if (valid) {
            try {
                new BigInteger(strNoPrefix, 16);
            } catch (Exception e) {
                valid = false;
            }
        }
        return valid;
    }

}
