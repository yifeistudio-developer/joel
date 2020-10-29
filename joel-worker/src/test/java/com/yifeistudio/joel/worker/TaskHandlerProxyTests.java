package com.yifeistudio.joel.worker;

import com.yifeistudio.joel.worker.config.DefaultTaskHandler;
import com.yifeistudio.joel.worker.config.Subscribe;
import com.yifeistudio.joel.worker.config.TaskHandlerProxy;
import com.yifeistudio.joel.worker.model.Task;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yi
 * @since 2020/10/29-4:32 下午
 */
public class TaskHandlerProxyTests {




    @Test
    public void invokeTest() throws Throwable {
        Object dd = new DefaultTaskHandler();
        Method[] declaredMethods = dd.getClass().getDeclaredMethods();
        Method method = Arrays.stream(declaredMethods).filter(v -> v.getAnnotation(Subscribe.class) != null).findFirst().get();
        new TaskHandlerProxy(dd).invoke(this, method, new Object[] {new Task()});
    }
}
