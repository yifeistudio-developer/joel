package com.yifeistudio.joel.worker.listener;

/**
 * @author yi
 * @since 2020/10/28-3:41 下午
 */
public interface TaskListener {

    default void onStart() {}

    default void onSuspend() {}

    default void onStop() {}

}
