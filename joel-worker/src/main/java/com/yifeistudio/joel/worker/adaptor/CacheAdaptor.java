package com.yifeistudio.joel.worker.adaptor;

/**
 * @author yi
 * @since 2020/10/27-11:26 上午
 */
public interface CacheAdaptor {

    /**
     * 不存在时设置
     * @param key 唯一标识
     * @return 设置结果
     */
    boolean setIfAbsent(String key);
}
