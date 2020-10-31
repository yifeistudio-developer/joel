package com.yifeistudio.joel.worker.config;

import java.lang.annotation.*;

/**
 * 周期性任务
 * @author yi
 * @since 2020/10/31-3:10 下午
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scheduled {

}
