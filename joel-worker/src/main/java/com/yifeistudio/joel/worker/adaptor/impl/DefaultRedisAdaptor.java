package com.yifeistudio.joel.worker.adaptor.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.adaptor.MessageAdaptor;
import com.yifeistudio.joel.worker.model.RedisConfig;
import com.yifeistudio.joel.worker.model.WorkerInfo;
import com.yifeistudio.joel.worker.util.AssertUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import java.util.concurrent.BlockingDeque;

/**
 * @author yi
 * @since 2020/10/27-11:27 上午
 */
class DefaultRedisAdaptor implements CacheAdaptor, MessageAdaptor {

    private final RedisClient redisClient;

    public DefaultRedisAdaptor(RedisConfig redisConfig) {
        AssertUtil.notNull(redisConfig, "redisConfig is null");
        RedisURI redisURI = RedisURI.builder()
                .withHost(redisConfig.getHost())
                .withPort(redisConfig.getPort())
                .build();
        redisClient = RedisClient.create(redisURI);
    }

    @Override
    public boolean setIfAbsent(String key) {



        return false;
    }

    @Override
    public void addSetMember(String setName, WorkerInfo workerInfo) {

    }

    @Override
    public void removeSetMember(String setName, WorkerInfo workerInfo) {

    }

    @Override
    public BlockingDeque<?> getBlockingDeque(String dequeName) {

        return null;
    }

    @Override
    public void broadcast(String topic, Object msg) {

    }
}
