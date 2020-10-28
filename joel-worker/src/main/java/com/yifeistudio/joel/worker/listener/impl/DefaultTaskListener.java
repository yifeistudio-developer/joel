package com.yifeistudio.joel.worker.listener.impl;

import com.yifeistudio.joel.worker.config.TaskContext;
import com.yifeistudio.joel.worker.listener.TaskListener;

/**
 * @author yi
 * @since 2020/10/28-3:42 下午
 */
public class DefaultTaskListener implements TaskListener {

    private final TaskContext taskContext;

    public DefaultTaskListener(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

}
