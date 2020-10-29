package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.listener.TaskListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * 任务执行上下文
 * @author yi
 * @since 2020/10/28-2:31 下午
 */
@Slf4j
public class TaskContext {

    private static final String HEARTBEAT_THREAD_NAME = "heartbeat-thread";

    private Thread heartbeatThread;

    private final WorkerContext workerContext;

    private Collection<TaskListener> taskListeners;

    public TaskContext(WorkerContext workerContext) {
        this.workerContext = workerContext;
    }

    public void init() {

        // 注册监听器

        // 激活状态机


    }


    public void activateHeartbeat() {
        heartbeatThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    log.warn("The heartbeat thread interrupted and will be stopped. ");
                    break;
                }
            }

        });
        heartbeatThread.setPriority(Thread.MAX_PRIORITY);
        heartbeatThread.setDaemon(true);
        heartbeatThread.setName(HEARTBEAT_THREAD_NAME);
        heartbeatThread.start();
    }



    /**
     * 关闭上下文
     * 回收资源
     */
    public void shutdown() {

        // 关闭心跳线程
        if (heartbeatThread != null) {
            heartbeatThread.interrupt();
        }
    }

    private enum State {



    }


}

