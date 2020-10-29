package com.yifeistudio.joel.worker.listener.impl;

import com.yifeistudio.joel.worker.adaptor.MessageAdaptor;
import com.yifeistudio.joel.worker.adaptor.impl.AdaptorFactory;
import com.yifeistudio.joel.worker.config.TaskContext;
import com.yifeistudio.joel.worker.config.TaskHandlerProxy;
import com.yifeistudio.joel.worker.listener.TaskListener;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.BlockingDeque;

/**
 * @author yi
 * @since 2020/10/28-3:42 下午
 */
@Slf4j
public class DefaultTaskListener implements TaskListener {

    private static final String TASK_LISTENER_THEAD_NAME = "task-listener-thread";

    private Thread taskListenerThread;

    private final TaskContext taskContext;

    private MessageAdaptor messageAdaptor;

    public DefaultTaskListener(TaskContext taskContext) {
        this.taskContext = taskContext;
        messageAdaptor = AdaptorFactory.getMessageAdaptor(new WorkerConfig());
    }

    @Override
    public void onStart() {

        taskListenerThread = new Thread(() -> {
            BlockingDeque<?> blockingDeque = messageAdaptor.getBlockingDeque("task-dequeue");
            while (!Thread.interrupted()) {
                try {
                    Object task = blockingDeque.take();
                    taskContext.fireTaskArrived(task);
                } catch (InterruptedException e) {
                    log.warn("The thread, {}, is interrupted and will exit.", TASK_LISTENER_THEAD_NAME);
                    break;
                }
            }
        });

        taskListenerThread.setDaemon(true);
        taskListenerThread.setName(TASK_LISTENER_THEAD_NAME);
        taskListenerThread.setPriority(Thread.MAX_PRIORITY);
        taskListenerThread.start();

    }
}
