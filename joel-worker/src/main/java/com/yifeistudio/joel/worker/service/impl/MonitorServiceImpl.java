package com.yifeistudio.joel.worker.service.impl;

import com.yifeistudio.joel.worker.config.WorkerContext;
import com.yifeistudio.joel.worker.service.MonitorService;


/**
 * @author yi
 * @since 2020/10/27-1:59 下午
 */
class MonitorServiceImpl implements MonitorService {

    private final WorkerContext workerContext;

    private Thread executeThread;

    public MonitorServiceImpl(WorkerContext workerContext) {
        this.workerContext = workerContext;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }


}
