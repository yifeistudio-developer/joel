package com.yifeistudio.joel.worker.model;

import lombok.Data;

/**
 * @author yi
 * @since 2020/10/28-12:19 下午
 */
@Data
public class ExecutorConfig {

    private Integer coreSize;

    private Integer maxSize;

    private Long keepAliveTime;

    // ...

}
