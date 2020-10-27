package com.yifeistudio.joel.worker.config;

import lombok.Data;

/**
 * @author yi
 * @since 2020/10/27-3:11 下午
 */
@Data
public class RedisConfig {

    private String host;

    private Integer port;

    private String password;


}
