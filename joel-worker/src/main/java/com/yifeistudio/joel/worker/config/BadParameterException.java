package com.yifeistudio.joel.worker.config;

/**
 * @author yi
 * @since 2020/10/26-7:25 下午
 */
public class BadParameterException extends JoelJobException {

    public BadParameterException(int code, String msg) {
        super(code, msg);
    }

}
