package com.yifeistudio.joel.worker.adaptor.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.model.RedisConfig;

/**
 * @author yi
 * @since 2020/10/27-11:27 上午
 */
class DefaultRedisAdaptorImpl implements CacheAdaptor {

    private final RedisConfig redisConfig;

    public DefaultRedisAdaptorImpl(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    @Override
    public boolean setIfAbsent(String key) {

        return false;
    }
}
