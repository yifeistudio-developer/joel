package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.listener.WorkerListener;
import com.yifeistudio.joel.worker.listener.impl.DefaultWorkerListener;
import com.yifeistudio.joel.worker.model.ExecutorConfig;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.model.WorkerInfo;
import com.yifeistudio.joel.worker.util.AssertUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.concurrent.*;


/**
 * 节点全局上下文
 *
 * @author yi
 * @since 2020/10/27-12:14 下午
 */
@Slf4j
public class WorkerContext {

    private WorkerInfo workerInfo;

    private Collection<WorkerListener> workerListeners;

    private ThreadPoolExecutor executor;

    @Getter
    private final WorkerConfig workerConfig;

    private StateMachine<State> stateMachine;

    public WorkerContext(WorkerConfig workerConfig) {
        AssertUtil.notNull(workerConfig, "workerConfig must not null.");
        this.workerConfig = workerConfig;
    }

    /**
     * 初始化
     */
    public synchronized void init() {

        // 填充节点信息
        loadWorkerInfo();

        // 加载配置
        loadConfig();

        // 初始化执行器
        initializeExecutor();

        // 注册监听器
        registryListener();

        // 注册状态机
        registryStateMachine();

        // 响应停机
        Runtime.getRuntime().addShutdownHook(new Thread(() -> stateMachine.fire(State.STOPPING)));
    }

    /**
     * 开机
     */
    public void fireStartup() {
        stateMachine.fire(State.STARTING);
    }

    /**
     * 关机
     */
    public void fireStop() {
        stateMachine.fire(State.STOPPING);
    }

    /**
     * 暂停
     */
    public void fireSuspend() {
        stateMachine.fire(State.SUSPENDING);
    }

    /**
     * 运行
     */
    public void fireRun() {
        stateMachine.fire(State.RUNNING);
    }

    /**
     * 关闭上下文
     * 回收资源
     */
    public void shutdown() {

        if (executor != null) {
            executor.shutdown();
        }

    }

    public Executor getExecutor() {

        return executor;
    }

    // ----------------------------------

    private void registryStateMachine() {
        stateMachine = new StateMachine<>(state -> {
            switch (state) {
                case STARTING:
                    workerListeners.forEach(v -> executor.execute(v::onStart));
                    break;
                case SUSPENDING:
                    workerListeners.forEach(v -> executor.execute(v::onSuspend));
                    break;
                case STOPPING:
                    workerListeners.forEach(v -> executor.execute(v::onStop));
                    break;
                default:
            }
        });
        stateMachine.startup();
    }

    /*
     * 上下文状态
     */
    protected enum State {
        STARTING,
        RUNNING,
        SUSPENDING,
        STOPPING
    }


    /*
     * 加载工作节点信息
     */
    private void loadWorkerInfo() {
        if (workerInfo != null) {
            return;
        }
        workerInfo = new WorkerInfo();
        String hostname = ManagementFactory.getRuntimeMXBean().getName();
        workerInfo.setHostname(hostname);
        int coreSize = Runtime.getRuntime().availableProcessors();
        workerInfo.setCoreSize(coreSize);
        workerInfo.setIpAddress(getWorkerIpAddress());
        if (log.isInfoEnabled()) {
            log.info("work information loaded.");
        }
    }

    /*
     * 加载全局配置
     */
    private void loadConfig() {

    }

    /**
     *
     */

    private void initializeExecutor() {
        ExecutorConfig executorConfig = workerConfig.getExecutor();

        ThreadFactory threadFactory = Thread::new;
        executor = new ThreadPoolExecutor(1,
                1,
                5,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
                threadFactory);
        if (log.isInfoEnabled()) {
            log.info("work configuration loaded.");
        }
    }

    /*
     * 注册监听器
     */
    private void registryListener() {
        workerListeners = new ArrayList<>();
        workerListeners.add(new DefaultWorkerListener(this));
    }

    /*
     * 获取本机IP地址
     */
    private String getWorkerIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            log.warn("got worker ip address failed.");
            return "";
        }
    }
}
