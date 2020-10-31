package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.model.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yi
 * @since 2020/10/29-4:09 下午
 */
@Slf4j
public class DefaultTaskHandler implements TaskHandler<Task> {

    //
    @Subscribe
    public void handle(Task task) {
        log.info("handle task... {}", task);
    }

    //

}
