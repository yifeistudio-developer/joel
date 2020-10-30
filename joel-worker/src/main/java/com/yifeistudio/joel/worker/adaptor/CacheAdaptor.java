package com.yifeistudio.joel.worker.adaptor;

import com.yifeistudio.joel.worker.model.WorkerInfo;

/**
 * 缓存适配器
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

    /**
     * 添加集合成员
     */
    void addSetMember(String setName, WorkerInfo workerInfo);

    /**
     * 删除集合成员
     */
    void removeSetMember(String setName, WorkerInfo workerInfo);

}
