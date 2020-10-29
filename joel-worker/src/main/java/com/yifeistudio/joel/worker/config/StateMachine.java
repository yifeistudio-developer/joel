package com.yifeistudio.joel.worker.config;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 状态机
 * @author yi
 * @since 2020/10/28-5:06 下午
 */
public class StateMachine<S extends Enum<?>> {

    private static final Lock LOCK = new ReentrantLock();

    private static final Condition STATE_CHANGED = LOCK.newCondition();

    private Thread stateThread;

    private final StateHandler<S> monitor;

    private S state;

    public StateMachine(StateHandler<S> monitor) {
        this.monitor = monitor;
    }

    /**
     * 变更状态
     * @param state 状态
     */
    public void fire(S state) {
        LOCK.lock();
        try {
            this.state = state;
            STATE_CHANGED.signal();
        } finally {
            LOCK.unlock();
        }
    }


    /**
     * 开机
     */
    public void startup() {
        if (stateThread == null) {
            stateThread = new Thread(() -> {
                while (!Thread.interrupted()) {
                    LOCK.lock();
                    try {
                        STATE_CHANGED.await();
                        monitor.handle(state);
                    } catch (InterruptedException e) {
                        break;
                    } finally {
                        LOCK.unlock();
                    }
                }
            });
            stateThread.setName("state-monitor-thread");
            stateThread.setDaemon(true);
            stateThread.start();
        }
    }

    /**
     * 停机
     */
    public void shutdown() {
        stateThread.interrupt();

    }

}
