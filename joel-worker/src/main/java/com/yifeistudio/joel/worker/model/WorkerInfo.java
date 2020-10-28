package com.yifeistudio.joel.worker.model;

import lombok.Data;

/**
 * @author yi
 * @since 2020/10/27-10:56 上午
 */
@Data
public class WorkerInfo {

    private String hostname;

    private String ipAddress;

    private Integer coreSize;

}
