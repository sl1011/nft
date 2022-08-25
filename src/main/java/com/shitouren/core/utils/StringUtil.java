package com.shitouren.core.utils;


import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 判断是否是有效的字符串，空串为无效串
     * @param str
     * @return
     */
    public static boolean isValidStr(String str) {
        return str != null && str.trim().length() > 0 && !str.trim().equalsIgnoreCase("null");
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /* 判断是否是有效的字符串，空串为无效串
     *
     * @param str
     * @return
     */
    public static boolean isValidStr(Object str) {

        return str != null && str.toString().trim().length() > 0;
    }

    public static String getValidStr(String str) {
        return str == null ? "" : str;
    }

    /**
     * if str is null then convert str to "".
     *
     * @param str
     * @return
     */
    public static String convertStrIfNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * if str is null then convret str to "".
     *
     * @param str
     * @return
     */
    public static String convertStrIfNull(Object str) {
        try {
            return str == null ? "" : str.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据字符串转换为布尔值.
     *
     * @param str
     * @return
     */
    public static boolean getStrToBoolean(String str) {
        return isValidStr(str) ? str.toLowerCase().trim().equals("true") : false;
    }

    /**
     * convert str value to int. if fail,then return 0.
     *
     * @param str
     * @return
     */
    public static int getStrToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * convert str value to int. if fail,then return 0.
     *
     * @return
     */
    public static int getObjToInt(Object obj) {
        try {
            return Integer.parseInt(getObjToStr(obj));
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    /**
     * 将OBJ转换成String 类型
     *
     * @param obj
     * @return
     */
    public static String getObjToStr(Object obj) {
        try {
            return obj == null ? null : obj.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * convert str value to int. if fail,then return defaultvalue.
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static int getStrToInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * convert String to long.
     *
     * @param str
     * @return
     */
    public static long getStrTolong(String str) {
        long result = 0;
        if (!isValidStr(str)) {
            return result;
        }
        try {
            result = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert String to double
     *
     * @param str
     * @return
     */
    public static double getStrTodouble(String str) {
        double result = 0;
        if (!isValidStr(str)) {
            return result;
        }
        try {
            result = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert String object to BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal getStrToBigDecimal(String str) {
        BigDecimal result = new BigDecimal(0);
        if (!isValidStr(str)) {
            return result;
        }
        try {
            result = new BigDecimal(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert String object to BigDecimal
     *
     * @param str
     * @return
     */
    public static String getBigDecimalToStr(BigDecimal str) {
        String result = "0.00";
        if (str == null) {
            return result;
        }
        try {
            result = str.toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert String to Integer.
     *
     * @return
     */
    public static Integer getStrToInteger(Object obj) {
        Integer result = new Integer(0);
        String str = obj == null ? "" : obj.toString();
        if (!isValidStr(str)) {
            return result;
        }
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert String to Long.
     *
     * @param str
     * @return
     */
    public static Long getStrToLong(String str) {
        Long result = new Long(0);
        if (!isValidStr(str)) {
            return result;
        }
        try {
            result = Long.valueOf(str.trim());
        } catch (NumberFormatException e) {
            // System.out.println(str+"无法转换为Long类型,错误信息-----------------------------------");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert String to Double
     *
     * @param str
     * @return
     */
    public static Double getStrToDouble(String str) {
        Double result = new Double(0);
        if (!isValidStr(str)) {
            return result;
        }
        try {
            result = Double.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * convert Object array to String use ",".
     *
     * @param obj
     * @return
     */
    public static String getArrToStr(Object[] obj) {
        if (obj == null) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        if (obj.length > 0) {
            buffer.append(obj[0]);
        }

        for (int m = 1; m < obj.length; m++) {
            buffer.append(",").append(obj[m]);
        }

        return buffer.toString();
    }

    /**
     * 去掉重复数据(1,2,3,2,4 => 1,2,3,4)
     *
     * @param metadata
     * @param tagStr
     * @return
     */
    public static String removeEqualStr(String metadata, String tagStr) {
        if (!StringUtil.isValidStr(metadata)) {
            return "";
        }
        Set<String> set = new HashSet<String>();
        String[] arr = metadata.split(tagStr);
        for (String temp : arr) {
            if (StringUtil.isValidStr(temp)) {
                set.add(temp);
            }
        }
        Iterator<String> it = set.iterator();
        StringBuffer returnMetadata = new StringBuffer();
        while (it.hasNext()) {
            returnMetadata.append(it.next() + tagStr);
        }
        return returnMetadata.toString().substring(0, returnMetadata.length() - 1);
    }

    /**
     * 查询是否有重复数据
     *
     * @param strArr
     * @param str
     * @param tagStr
     * @return boolean
     */
    public static boolean hasEqualStr(String strArr, String str, String tagStr) {
        boolean bool = false;
        if (StringUtil.isValidStr(strArr)) {
            String[] arr = strArr.split(tagStr);
            for (String temp : arr) {
                if (temp.equals(str)) {
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }

    /**
     * convert type to utf-8
     *
     * @param str
     * @return utf-8 string
     * @author libo
     */
    public static String toUtf8String(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }


    /**
     * format人员信息完整性
     *
     * @param formatString 被更新字符串
     * @param updateIndex  选择更新位数
     * @param updateValue  更新为值(true=1,false=0)
     * @return String
     */
    public static String formatIntegrity(String formatString, int updateIndex, char updateValue) {
        if (!isValidStr(formatString)) {
            return formatString;
        }
        if (updateIndex < 1) {
            return formatString;
        }
        if (updateIndex > formatString.length()) {
            return formatString;
        }
        char[] formatStringChar = formatString.toCharArray();
        formatStringChar[updateIndex] = updateValue;

        return String.valueOf(formatStringChar);
    }

    /**
     * 为带有？的sql插入变量值
     *
     * @param sql    带有？的sql
     * @param params 查询变量
     * @return String
     */
    public static String converSql(String sql, String[] params) {
        if (!isValidStr(sql) || params == null || params.length == 0) {
            return sql;
        }
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            if (isValidStr(param)) {
                sql = sql.replaceFirst("\\?", converSpecialChar(param));
            }
        }
        return sql;
    }

    /**
     * 转换特殊字符
     *
     * @param str 含有特殊字符的字符串
     * @return String
     */
    public static String converSpecialChar(String str) {
        if (!isValidStr(str)) {
            return str;
        }
        str = str.trim();
        if (str.indexOf("\\") >= 0) {
            str = str.replaceAll("\\\\", "\\\\\\\\\\\\\\\\");
        }
        if (str.indexOf("'") >= 0) {
            str = str.replaceAll("'", "\\\\'");
        }
        if (str.indexOf("\"") >= 0) {
            str = str.replaceAll("\"", "\\\\\"");
        }
        if (str.indexOf("%") >= 0) {
            str = str.replaceAll("%", "\\\\%");
        }
        return str;
    }

    /**
     * 获取字符串字节长度（包含中文和中文符号）
     *
     * @param str 含有中文和中文符号的字符串
     * @return int
     */
    public static int getLength(String str) {
        return str.replaceAll("[\u4E00-\u9FA5\u3000-\u303F\uFF00-\uFFEF]", "rr").length();
    }


    public static String getReplace(String str) {
        if (!isValidStr(str)) {
            return "";
        }
        str = str.replaceAll("\\$", "\\\\\\$");
        return str;
    }



    /**
     * 把 Integer类型转换成String类型 如果Integer类型数据为null则返回 "0"
     *
     * @param value
     * @return
     */
    public static String getIntToStr(Integer value) {
        if (value != null) {
            return value + "";
        }
        return "0";
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }



    /**
     * 检查手机号是否合法
     * 手机号码由11位数字组成，
     * 匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 14+任意数
     * 15+任意数
     * 16+任意数
     * 17+任意数
     * 18+任意数
     *
     * @param str, 待检查字符串
     * @return boolean true合法  false非法
     */
    public static boolean isMobilePhoneStr(String str) {
        if (!isValidStr(str)) {
            return false;
        }
        String regExp = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(str);
        boolean bIsValid = false;

        bIsValid = m.matches();

        return bIsValid;
    }

    /**
     * 检查手机号(全部或部分号码)是否合法
     *
     * @param str, 待检查字符串
     * @return boolean true合法  false非法
     */
    public static boolean isMobilePhoneSubStr(String str) {
        long cell = 0L;
        try {
            cell = Long.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }

        if (cell > -1 && cell < 20000000000L) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * sql语句 like的时候拼接%%
     *
     * @param str 需要拼接的字符串
     * @return 拼接后的字符串， %str%
     */
    public static String sqlLikeSplicing(String str) {

        return "%" + str + "%";
    }


    /**
     * 查询字符串中出现指定字符串的次数
     *
     * @param str     原字符串
     * @param sToFind 需要查找的字符串
     * @return 返回在原字符串中sToFind出现的次数
     */
    public static int countStr(String str, String sToFind) {
        int num = 0;
        while (str.contains(sToFind)) {
            str = str.substring(str.indexOf(sToFind) + sToFind.length());
            num++;
        }
        return num;
    }


    public static String jion(List<Integer> list, String start, String separator,String end) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        sb.append(end);
        return sb.toString();
    }

    public static String jion(List<Integer> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String sortJion(Integer i1, Integer i2, String separator) {
        return separator+(i1 > i2 ? i1 : i2)+separator+(i1 < i2 ? i1 : i2)+separator;
    }
}
