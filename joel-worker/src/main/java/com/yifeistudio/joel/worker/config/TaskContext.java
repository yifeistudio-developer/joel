package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.listener.TaskListener;

import java.util.Collection;

/**
 * 任务执行上下文
 * @author yi
 * @since 2020/10/28-2:31 下午
 */
public class TaskContext {


    private final WorkerContext workerContext;

    private Collection<TaskListener> taskListeners;

    public TaskContext(WorkerContext workerContext) {
        this.workerContext = workerContext;
    }



}

