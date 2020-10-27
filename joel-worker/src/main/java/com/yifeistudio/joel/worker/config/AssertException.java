package com.yifeistudio.joel.worker.config;


/**
 * @author yi
 * @since 2020/10/26-7:05 下午
 */
public class AssertException extends JoelJobException {

    public AssertException(int code, String msg) {
        super(code, msg);
    }

}
