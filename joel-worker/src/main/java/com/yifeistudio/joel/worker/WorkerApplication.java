package com.yifeistudio.joel.worker;

import com.yifeistudio.joel.worker.config.WorkerContext;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yi
 * @since 2020/10/27-1:53 下午
 */
@Slf4j
public class WorkerApplication {

    public static void main(String[] args) {
        WorkerApplication.startup(new WorkerConfig());
        LockSupport.parkUntil(Instant.now().plus(5, ChronoUnit.MINUTES).toEpochMilli());
    }

    public static void startup(WorkerConfig config) {
        WorkerContext workerContext = new WorkerContext(config);
        workerContext.init();
        workerContext.fireStartup();
    }
}
