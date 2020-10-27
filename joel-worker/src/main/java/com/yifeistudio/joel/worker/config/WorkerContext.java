package com.yifeistudio.joel.worker.config;

import lombok.Data;



/**
 * @author yi
 * @since 2020/10/27-12:14 下午
 */
@Data
public class WorkerContext {

    private String masterId;

    private RedisConfig redisConfig;

    public void init() {
        // 加载配置

        // 注册监听器

    }

    public void fire() {

    }


}
