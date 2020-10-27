package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.model.event.WorkerEvent;
import lombok.Getter;

import java.util.concurrent.Executor;


/**
 * @author yi
 * @since 2020/10/27-12:14 下午
 */
public class WorkerContext {

    @Getter
    private final WorkerConfig workerConfig;

    @Getter
    private Executor executor;

    public WorkerContext(WorkerConfig workerConfig) {
        this.workerConfig = workerConfig;
    }

    public void init() {

        // 加载配置



        // 注册监听器

    }

    public void fire(WorkerEvent event) {

    }


}
