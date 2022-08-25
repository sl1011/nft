package com.shitouren.core.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * All rights Reserved, Designed By www.zengxm.cn
 *
 * @version V1.0
 * @Title: MathUtils.java
 * @Package cn.zxm.common.util
 * @Description:
 * @author: ZXM
 * @date: 2018年8月15日 下午5:20:44
 * @company:
 * @Copyright:
 */
public class MathUtils {
    /**
     * @param value
     * @return
     * @Title: objectConvertBigDecimal
     * @Description: Object类型的数字转BigDecimal
     * @author: ZXM
     */
    public static BigDecimal objectConvertBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass()
                        + " into a BigDecimal.");
            }
        }
        return ret;
    }
}