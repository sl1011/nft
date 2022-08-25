package com.shitouren.core.config.redis;

/**
 * rediskey过期处理接口
 * @author Administrator
 */
public interface RedisKeyExpirationService {

    /**
     * 处理key过期后的业务
     */
    void handle(String key);
}
