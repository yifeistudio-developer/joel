package com.yifeistudio.joel.worker.adaptor;

import java.util.concurrent.BlockingDeque;

/**
 * 消息适配器
 * @author yi
 * @since 2020/10/28-3:30 下午
 */
public interface MessageAdaptor<T> {

    BlockingDeque<T> createBlockingDeque(String dequeName);


}
