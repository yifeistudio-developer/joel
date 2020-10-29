package com.yifeistudio.joel.worker.config;

/**
 * @author yi
 * @since 2020/10/29-8:25 下午
 */
public interface TaskHandler<T> {

    void handle(T task);

}
