package com.yifeistudio.joel.worker.listener.impl;

import com.yifeistudio.joel.worker.config.WorkerContext;
import com.yifeistudio.joel.worker.listener.WorkerListener;
import com.yifeistudio.joel.worker.service.IdentityService;
import com.yifeistudio.joel.worker.service.impl.ServiceFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yi
 * @since 2020/10/27-11:25 上午
 */
public class DefaultWorkerListenerImpl implements WorkerListener {

    private static final Lock startedLock = new ReentrantLock();

    private static final Condition startedCondition = startedLock.newCondition();

    private final WorkerContext workerContext;

    private final IdentityService identityService;

    public DefaultWorkerListenerImpl(WorkerContext workerContext) {
        this.workerContext = workerContext;
        this.identityService = ServiceFactory.getIdentityService(workerContext);
    }

    public void init() {

        new Thread(() -> {

            startedLock.lock();
            try {
                startedCondition.await();
            } catch (InterruptedException e) {

            } finally {
                startedLock.unlock();
            }
        });


    }

}
