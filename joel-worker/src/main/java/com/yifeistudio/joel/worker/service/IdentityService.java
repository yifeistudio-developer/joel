package com.yifeistudio.joel.worker.service;

/**
 * @author yi
 * @since 2020/10/27-11:45 上午
 */
public interface IdentityService {

    /**
     * 抢占主节点身份
     * @return 是否抢占成功
     */
    boolean seizeMasterRole();

    /**
     * 出让主节点身份
     */
    void releaseMasterRole();

    /**
     * 注册
     */
    void signup();

    /**
     * 注销
     */
    void logout();

}
