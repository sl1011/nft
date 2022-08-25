package com.shitouren.core.utils;


import com.shitouren.core.bean.vo.user.UserLoginVo;
import com.shitouren.core.bean.vo.user.UserLoginWx;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;


public class TokenUtil {

    private static CloudRedisTemplate cloudRedisTemplate = DdSpringUtils.getBean(CloudRedisTemplate.class);

    /**
     * 获取token。
     *
     * @param user
     * @return
     */
    public static String getToken(UserLoginWx user) {

        String loginAccount = String.valueOf(user.getUserId());
        Integer type = 1;
        String token = cloudRedisTemplate.get(loginAccount + 1);
        System.out.println("--->token:" + token);
        if (StringUtil.isValidStr(token)) {
            /**
             * 删除原有token
             */
            boolean set = cloudRedisTemplate.set(token, ExceptionConstant.账号在其他设备登录.getCode(), 60 * 60);
            if (!set) {
                return null;
            }
        }
        token = StringUtil.getUUID();
        boolean set = cloudRedisTemplate.set(token, user, 7 * 24 * 60 * 60);
        boolean set1 = cloudRedisTemplate.set(loginAccount + 1, token, 7 * 24 * 60 * 60);
        if (!set && !set1) {
            return null;
        }
        if (!set && set1) {
            cloudRedisTemplate.delete(loginAccount + 1);
            return null;
        }
        if (!set1 && set) {
            cloudRedisTemplate.delete(token);
            return null;
        }
        return token;
    }

    public static String getTokenForTrade(UserLoginWx user) {

        String loginAccount = String.valueOf(user.getUserId());
        Integer type = 2;
        String token = cloudRedisTemplate.get(loginAccount + type);
        System.out.println("--->token:" + token);
        if (StringUtil.isValidStr(token)) {
            boolean set = cloudRedisTemplate.set(token, ExceptionConstant.账号在其他设备登录.getCode(), 60 * 60);
            if (!set) {
                return null;
            }
        }
        token = StringUtil.getUUID();
        boolean set = cloudRedisTemplate.set(token, user, 7 * 24 * 60 * 60);
        boolean set1 = cloudRedisTemplate.set(loginAccount + type, token, 7 * 24 * 60 * 60);
        if (!set && !set1) {
            return null;
        }
        if (!set && set1) {
            cloudRedisTemplate.delete(loginAccount + type);
            return null;
        }
        if (!set1 && set) {
            cloudRedisTemplate.delete(token);
            return null;
        }
        return token;
    }

    public static void deleteToken(String loginAccount, String token) {
        cloudRedisTemplate.delete(loginAccount);
        cloudRedisTemplate.delete(token);
    }
}
