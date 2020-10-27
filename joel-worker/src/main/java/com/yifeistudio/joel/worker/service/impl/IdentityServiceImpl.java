package com.yifeistudio.joel.worker.service.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.adaptor.impl.AdaptorFactory;
import com.yifeistudio.joel.worker.config.WorkerContext;
import com.yifeistudio.joel.worker.service.IdentityService;

/**
 * @author yi
 * @since 2020/10/27-11:49 上午
 */
class IdentityServiceImpl implements IdentityService {

    private final CacheAdaptor cacheAdaptor;

    private final WorkerContext workerContext;

    public IdentityServiceImpl(WorkerContext workerContext) {
        this.workerContext = workerContext;
        this.cacheAdaptor = AdaptorFactory.getCacheAdaptor(workerContext);
    }

    @Override
    public boolean seizeMasterRole() {
        String masterId = workerContext.getMasterId();
        boolean setResult = cacheAdaptor.setIfAbsent(masterId);
        return false;
    }

    @Override
    public void releaseMasterRole() {

    }
}
