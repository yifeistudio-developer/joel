package com.yifeistudio.joel.worker.adaptor.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.model.WorkerConfig;

/**
 * @author yi
 * @since 2020/10/27-12:32 下午
 */
public final class AdaptorFactory {

    private AdaptorFactory() { }

    public static CacheAdaptor getCacheAdaptor(WorkerConfig context) {
        return new DefaultRedisAdaptorImpl(context.getRedisConfig());
    }



}
