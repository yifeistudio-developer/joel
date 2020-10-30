package com.yifeistudio.joel.worker.adaptor;

import java.util.concurrent.BlockingDeque;

/**
 * 消息适配器
 * @author yi
 * @since 2020/10/28-3:30 下午
 */
public interface MessageAdaptor {

    /**
     * 消息阻塞队列
     * @param dequeName 队列名
     * @return 消息
     */
    BlockingDeque<?> getBlockingDeque(String dequeName);

    /**
     * 广播消息
     * @param topic 主题
     * @param msg 消息内容
     */
    void broadcast(String topic, Object msg);
}
