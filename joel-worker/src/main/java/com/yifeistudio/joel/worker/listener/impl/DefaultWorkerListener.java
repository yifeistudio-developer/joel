package com.yifeistudio.joel.worker.listener.impl;

import com.yifeistudio.joel.worker.listener.WorkerListener;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.service.IdentityService;
import com.yifeistudio.joel.worker.service.impl.ServiceFactory;

/**
 * @author yi
 * @since 2020/10/27-11:25 上午
 */
public class DefaultWorkerListener implements WorkerListener {

    private final WorkerConfig workerConfig;

    private final IdentityService identityService;

    public DefaultWorkerListener(WorkerConfig workerConfig) {
        this.workerConfig = workerConfig;
        this.identityService = ServiceFactory.getIdentityService(workerConfig);
    }

    @Override
    public void onStart() {
        boolean seizeResult = identityService.seizeMasterRole();
        if (seizeResult) {
            // 激活管理线程

        }
        // 注册成为工作节点

    }



}
