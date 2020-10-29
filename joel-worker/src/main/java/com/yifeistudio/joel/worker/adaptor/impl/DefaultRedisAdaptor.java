package com.yifeistudio.joel.worker.adaptor.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.adaptor.MessageAdaptor;
import com.yifeistudio.joel.worker.model.RedisConfig;

import java.util.concurrent.BlockingDeque;

/**
 * @author yi
 * @since 2020/10/27-11:27 上午
 */
class DefaultRedisAdaptor implements CacheAdaptor, MessageAdaptor {

    private final RedisConfig redisConfig;

    public DefaultRedisAdaptor(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    @Override
    public boolean setIfAbsent(String key) {

        return false;
    }

    @Override
    public BlockingDeque<?> getBlockingDeque(String dequeName) {

        return null;
    }
}
