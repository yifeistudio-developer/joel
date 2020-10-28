package com.yifeistudio.joel.worker.config;

/**
 * 任务执行上下文
 * @author yi
 * @since 2020/10/28-2:31 下午
 */
public class TaskContext {

    private final WorkerContext workerContext;

    public TaskContext(WorkerContext workerContext) {
        this.workerContext = workerContext;
    }

    

}
