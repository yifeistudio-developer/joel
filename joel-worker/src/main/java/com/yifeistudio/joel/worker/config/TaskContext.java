package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.listener.TaskListener;
import com.yifeistudio.joel.worker.model.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务执行上下文
 *
 * @author yi
 * @since 2020/10/28-2:31 下午
 */
@Slf4j
public class TaskContext {

    private StateMachine<State> stateStateMachine;

    private final WorkerContext workerContext;

    private Collection<TaskListener> taskListeners;

    private Map<Class<?>, TaskHandlerProxy> handlerMap;

    public TaskContext(WorkerContext workerContext) {
        this.workerContext = workerContext;
    }

    public void init() {

        // 注册监听器


        // 激活状态机
        startupStateMachine();

        // 绑定 handler
        bindTaskHandler();

    }

    public void fireStart() {
        stateStateMachine.fire(State.STARTING);
    }

    /**
     * 关闭上下文
     * 回收资源
     */
    public void shutdown() {

    }


    private void startupStateMachine() {
        stateStateMachine = new StateMachine<>(state -> {


        });
    }

    private void bindTaskHandler() {
        if (handlerMap == null) {
            handlerMap = new HashMap<>();
        }
        handlerMap.put(Task.class, new TaskHandlerProxy(new DefaultTaskHandler()));
    }

    public void fireTaskArrived(Object task) {
        workerContext.getExecutor().execute(() -> {
            TaskHandlerProxy proxy = handlerMap.get(task.getClass());
            try {
                proxy.handle(task);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private enum State {
        STARTING,
        RUNNING,
        SUSPENDING,
        STOPPING
    }

}

