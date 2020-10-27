package com.yifeistudio.joel.worker.service.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.adaptor.impl.AdaptorFactory;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.service.IdentityService;

/**
 * @author yi
 * @since 2020/10/27-11:49 上午
 */
class IdentityServiceImpl implements IdentityService {

    private final CacheAdaptor cacheAdaptor;

    private final WorkerConfig workerConfig;

    public IdentityServiceImpl(WorkerConfig workerConfig) {
        this.workerConfig = workerConfig;
        this.cacheAdaptor = AdaptorFactory.getCacheAdaptor(workerConfig);
    }

    @Override
    public boolean seizeMasterRole() {
        String masterId = workerConfig.getMasterId();
        return cacheAdaptor.setIfAbsent(masterId);
    }

    @Override
    public void releaseMasterRole() {

    }

    @Override
    public void signup() {

    }

    @Override
    public void logout() {

    }
}
