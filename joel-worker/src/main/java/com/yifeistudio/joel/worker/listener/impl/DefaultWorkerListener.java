package com.yifeistudio.joel.worker.listener.impl;

import com.yifeistudio.joel.worker.config.WorkerContext;
import com.yifeistudio.joel.worker.listener.WorkerListener;
import com.yifeistudio.joel.worker.service.IdentityService;
import com.yifeistudio.joel.worker.service.impl.ServiceFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yi
 * @since 2020/10/27-11:25 上午
 */
@Slf4j
public class DefaultWorkerListener implements WorkerListener {

    private static final String HEARTBEAT_THREAD = "heartbeat-thread";

    private Thread heartBeaThread;

    private final IdentityService identityService;

    private final WorkerContext workerContext;

    public DefaultWorkerListener(WorkerContext workerContext) {
        this.workerContext = workerContext;
        this.identityService = ServiceFactory.getIdentityService(workerContext.getWorkerConfig());
    }

    @Override
    public void onStart() {

        boolean seizeResult = identityService.seizeMasterRole();

        if (seizeResult) {
            // 激活管理线程
            log.info("seize master role success. register as master");
        } else {
            log.info("seize master role failed. register as worker");
        }

        // 注册成为工作节点
        identityService.signup();

        // 激活心跳
        if (heartBeaThread == null) {
            heartBeaThread = new Thread(() -> {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        log.warn("heartbeat interrupted");
                        break;
                    }
                    if (log.isDebugEnabled()) {
                        log.debug("heartbeat...");
                    }
                }
            });
            heartBeaThread.setDaemon(true);
            heartBeaThread.setName(HEARTBEAT_THREAD);
            heartBeaThread.start();
        }

        workerContext.fireRun();
    }


    @Override
    public void onStop() {
        heartBeaThread.interrupt();
    }
}
