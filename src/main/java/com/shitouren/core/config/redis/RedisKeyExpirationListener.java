package com.shitouren.core.config.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * redis事件监听
 * @author Administrator
 * @Autho： 王涛
 * @DATE： 2020/3/7 9:36
 */
//@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    /**
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 当一个key到期后，会触发本方法
     *      要求业务发起方，key的命名规则 由三部分组成，
     *          第一部分 实现业务的实现类  该类要实现RedisKeyExpirationService接口
     *          第二部分 业务关键参数，比如ID  mac 最好是一个
     *
     *          如：RedisKeyExpirationServiceImpl:00_0a_f5_26_36_25:
     *          RedisKeyExpirationServiceImpl = 执行过期后的操作实现类
     *          00_0a_f5_26_36_25 = 设备mac地址
     *          除两部分的分割使用 ：外 其他字符串的 : 都需要替换掉
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        String[] split = expiredKey.split(":");
        if(split.length != 2){
            return;
        }
        RedisKeyExpirationService redisKeyExpirationService = null;
        try {
            //redisKeyExpirationService = DdSpringUtils.getBean(split[0], RedisKeyExpirationService.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if(redisKeyExpirationService == null ){
            return;
        }
        redisKeyExpirationService.handle(split[1]);

    }
}
