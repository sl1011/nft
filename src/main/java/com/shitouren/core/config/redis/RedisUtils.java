package com.shitouren.core.config.redis;


import com.shitouren.core.utils.DdSpringUtils;

/**
 * redis工具类
 * @author Administrator
 * @Autho： 王涛
 * @DATE： 2018/12/24 15:00
 */
@SuppressWarnings("ALL")
public class RedisUtils {

    private static CloudRedisTemplate cloudRedisTemplate = DdSpringUtils.getBean(CloudRedisTemplate.class);


    public static <T> T getUserLoginInfo(Class<T> T,String token){
        try {
            return (T) cloudRedisTemplate.get(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
