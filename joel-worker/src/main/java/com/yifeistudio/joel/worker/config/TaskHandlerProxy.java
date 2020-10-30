package com.yifeistudio.joel.worker.config;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yi
 * @since 2020/10/29-4:27 下午
 */
@Slf4j
public class TaskHandlerProxy implements InvocationHandler {

    private final Object target;

    private final Method targetMethod;

    public TaskHandlerProxy(Object target) {
        this.target = target;
        Class<?> clz = target.getClass();
        Method subscribedMethod = null;
        for (Method declaredMethod : clz.getDeclaredMethods()) {
            if (declaredMethod.getAnnotation(Subscribe.class) != null) {
                subscribedMethod = declaredMethod;
                break;
            }
        }
        if (subscribedMethod == null) {
            log.warn("No declared method annotated Subscribe");
        }
        targetMethod = subscribedMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    public void handle(Object task) throws Throwable {
        if (targetMethod == null) {
            log.warn("A task handled but no method to invoke: {}", task);
            return;
        }
        invoke(this, targetMethod, new Object[]{task});
    }
}
///～
