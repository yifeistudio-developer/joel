package com.yifeistudio.joel.worker.model;

import lombok.Data;

/**
 * @author yi
 * @since 2020/10/27-7:31 下午
 */
@Data
public class WorkerConfig {

    private String masterId;

    private ExecutorConfig executor;

    private RedisConfig redis;

}
