package com.yifeistudio.joel.worker.config;

import lombok.Getter;

/**
 * @author yi
 * @since 2020/10/26-7:05 下午
 */
@Getter
public class JoelJobException extends RuntimeException {

    private final Integer code;

    private final String msg;

    public JoelJobException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public JoelJobException(int code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
        this.msg = msg;
    }

}
