package com.yifeistudio.joel.worker;

import com.yifeistudio.joel.worker.config.WorkerContext;
import com.yifeistudio.joel.worker.model.WorkerConfig;

/**
 * @author yi
 * @since 2020/10/27-1:53 下午
 */
public class WorkerApplication {

    public static void main(String[] args) {
        WorkerApplication.startup(new WorkerContext(new WorkerConfig()));
    }

    public static void startup(WorkerContext context) {
        context.init();
        context.startup();
    }

}
