package com.yifeistudio.joel.worker.config;

/**
 * @author yi
 * @since 2020/10/28-5:43 下午
 */
public @FunctionalInterface interface StateHandler<S> {

    void handle(S state);

}
