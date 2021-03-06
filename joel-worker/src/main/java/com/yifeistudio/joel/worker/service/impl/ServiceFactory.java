package com.yifeistudio.joel.worker.service.impl;

import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.service.IdentityService;

/**
 * @author yi
 * @since 2020/10/27-12:26 下午
 */
public final class ServiceFactory {

    private ServiceFactory() { }

    public static IdentityService getIdentityService(WorkerConfig workerConfig) {
        return new IdentityServiceImpl(workerConfig);
    }

}
