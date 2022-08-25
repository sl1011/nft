package com.shitouren.core.utils;

/**
 * @Autho： 王涛
 * @DATE： 2020/6/20 11:44
 */
public class IntegerUtils {

    /**
     * 将 Integer 转换成 int  如果 Integer == null 则返回0
     * @param integer
     * @return
     */
    public static int IntegerToInt(Integer integer){
        if(integer == null){
            return 0;
        }
        return integer;
    }
}
