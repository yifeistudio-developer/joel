package com.yifeistudio.joel.worker.adaptor.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.adaptor.MessageAdaptor;
import com.yifeistudio.joel.worker.model.WorkerConfig;

/**
 * 适配器工厂
 * @author yi
 * @since 2020/10/27-12:32 下午
 */
public final class AdaptorFactory {

    private AdaptorFactory() { }

    public static CacheAdaptor getCacheAdaptor(WorkerConfig workerConfig) {
        return new DefaultRedisAdaptor(workerConfig.getRedis());
    }

    public static MessageAdaptor getMessageAdaptor(WorkerConfig workerConfig) {
        return new DefaultRedisAdaptor(workerConfig.getRedis());
    }

}
