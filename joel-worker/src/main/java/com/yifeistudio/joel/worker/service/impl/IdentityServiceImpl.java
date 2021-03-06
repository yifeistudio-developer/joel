package com.yifeistudio.joel.worker.service.impl;

import com.yifeistudio.joel.worker.adaptor.CacheAdaptor;
import com.yifeistudio.joel.worker.adaptor.MessageAdaptor;
import com.yifeistudio.joel.worker.adaptor.impl.AdaptorFactory;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.service.IdentityService;

/**
 * @author yi
 * @since 2020/10/27-11:49 上午
 */
class IdentityServiceImpl implements IdentityService {

    private Role role;

    private final CacheAdaptor cacheAdaptor;

    private final MessageAdaptor messageAdaptor;

    private final WorkerConfig workerConfig;

    public IdentityServiceImpl(WorkerConfig workerConfig) {
        this.workerConfig = workerConfig;
        this.cacheAdaptor = AdaptorFactory.getCacheAdaptor(workerConfig);
        this.messageAdaptor = AdaptorFactory.getMessageAdaptor(workerConfig);
    }

    @Override
    public boolean seizeMasterRole() {
        String masterId = workerConfig.getMasterId();
        boolean isMaster = cacheAdaptor.setIfAbsent(masterId);
        role = isMaster ? Role.MASTER : Role.WORKER;
        if (role == Role.MASTER) {
            // 告诉广播群组我是老大！
            messageAdaptor.broadcast("identity-broadcast", "I'm the master!");
        }
        return isMaster;
    }

    @Override
    public void releaseMasterRole() {
        if (role == Role.MASTER) {
            // 广播群组我放弃了老大的地位
            role = Role.WORKER;
            messageAdaptor.broadcast("", "I lost master role!");
            // 下线管理线程

        }
    }

    @Override
    public void signup() {
        // 注册成为工作组成员

    }

    @Override
    public void logout() {

        // 广播下线
    }

    private enum Role {
        MASTER,
        WORKER
    }

}
