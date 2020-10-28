package com.yifeistudio.joel.worker.listener;

/**
 * @author yi
 * @since 2020/10/27-10:58 上午
 */
public interface WorkerListener {


    default void onStart() {}

    default void onSuspend() { }

    default void onStop() { }

}
