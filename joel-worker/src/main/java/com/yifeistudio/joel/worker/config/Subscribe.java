package com.yifeistudio.joel.worker.config;

import java.lang.annotation.*;

/**
 * 异步任务
 * @author yi
 * @since 2020/10/27-4:05 下午
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

}
