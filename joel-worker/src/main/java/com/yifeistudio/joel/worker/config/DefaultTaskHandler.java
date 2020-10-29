package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.model.Task;

/**
 * @author yi
 * @since 2020/10/29-4:09 下午
 */
public class DefaultTaskHandler {


    @Subscribe
    public void handle(Task task) {

        System.out.println(task);
    }

}
