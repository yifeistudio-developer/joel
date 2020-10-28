package com.yifeistudio.joel.worker.config;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yi
 * @since 2020/10/28-3:51 下午
 */
public abstract class AbstractContext {

    protected static final Lock LOCK = new ReentrantLock();

    protected static final Condition STATE_CHANGED = LOCK.newCondition();

    protected State state;

    /**
     * 初始化
     */
    public abstract void init();


    /**
     * 开机
     */
    public void fireStartup() {
        fire(State.STARTING);
    }

    /**
     * 关机
     */
    public void fireStop() {
        fire(State.STOPPING);
    }

    /**
     * 暂停
     */
    public void fireSuspend() {
        fire(State.SUSPENDING);
    }

    /**
     * 继续
     */
    public void fireRun() {
        fire(State.RUNNING);
    }


    /*
     * 上下文状态
     */
    protected enum State {
        STARTING,
        RUNNING,
        SUSPENDING,
        STOPPING
    }

    /*
     * 发布状态变更通知
     */
    protected void fire(State state) {
        LOCK.lock();
        try {
            this.state = state;
            STATE_CHANGED.signal();
        } finally {
            LOCK.unlock();
        }
    }

}
