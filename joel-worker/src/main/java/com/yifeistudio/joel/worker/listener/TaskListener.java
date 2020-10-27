package com.yifeistudio.joel.worker.listener;

/**
 * @author yi
 * @since 2020/10/27-1:40 下午
 */
public interface TaskListener {

    /**
     * 当消息抵达
     */
    void onArrived(Object handler);

}
