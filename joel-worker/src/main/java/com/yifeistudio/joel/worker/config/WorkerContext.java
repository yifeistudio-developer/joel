package com.yifeistudio.joel.worker.config;

import com.yifeistudio.joel.worker.listener.WorkerListener;
import com.yifeistudio.joel.worker.listener.impl.DefaultWorkerListener;
import com.yifeistudio.joel.worker.model.WorkerConfig;
import com.yifeistudio.joel.worker.model.WorkerInfo;
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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author yi
 * @since 2020/10/27-12:14 下午
 */
@Slf4j
public class WorkerContext {

    private static final String STATE_MONITOR_THREAD = "state-monitor-thread";

    private static final Lock LOCK = new ReentrantLock();

    private static final Condition STATE_CHANGED = LOCK.newCondition();

    private State state;

    private WorkerInfo workerInfo;

    private Collection<WorkerListener> workerListeners;

    @Getter
    private Executor executor;

    @Getter
    private final WorkerConfig workerConfig;

    public WorkerContext(WorkerConfig workerConfig) {
        this.workerConfig = workerConfig;
    }

    public void init() {

        // 填充节点信息
        loadWorkerInfo();

        // 加载配置
        loadConfig();

        // 注册监听器
        registryListener();

        // 启动消息通知线程
        activateStateMonitor();

        // 响应停机
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            fire(State.STOP);
        }));
    }


    /**
     * 开机
     */
    public void startup() {
        fire(State.START);
    }

    /**
     * 关机
     */
    public void stop() {
        fire(State.STOP);
    }

    /**
     * 暂停
     */
    public void suspend() {
        fire(State.SUSPEND);
    }

    /**
     * 继续
     */
    public void resume() {
        fire(State.RESUME);
    }

    // ----------------------------------

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

        // TODO: 2020/10/28 配置执行线程池
        if (executor == null) {
            ThreadFactory threadFactory = Thread::new;

            executor = new ThreadPoolExecutor(1,
                    1,
                    5,
                    TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
                    threadFactory);
        }
        if (log.isInfoEnabled()) {
            log.info("work configuration loaded.");
        }
    }

    /*
     * 注册监听器
     */
    private void registryListener() {
        if (workerListeners == null) {
            workerListeners = new ArrayList<>();
            workerListeners.add(new DefaultWorkerListener(workerConfig));
        }
    }

    /*
     * 状态监视器
     */
    private void activateStateMonitor() {
        Thread stateMonitor = new Thread(() -> {
            while (!Thread.interrupted()) {
                LOCK.lock();
                try {
                    STATE_CHANGED.await();
                    if (state == State.START) {
                        workerListeners.forEach(v -> executor.execute(v::onStart));
                    }
                } catch (InterruptedException ignore) {
                } finally {
                    LOCK.unlock();
                }
            }
        });
        stateMonitor.setUncaughtExceptionHandler((thread, throwable) -> {
            // TODO: 2020/10/28 未知异常处理器
        });
        stateMonitor.setName(STATE_MONITOR_THREAD);
        stateMonitor.setDaemon(true);
        stateMonitor.start();
    }

    /*
     * 上下文状态
     */
    private enum State {
        START,
        SUSPEND,
        RESUME,
        STOP
    }

    /*
     * 发布状态变更通知
     */
    private void fire(State state) {
        LOCK.lock();
        try {
            this.state = state;
            STATE_CHANGED.signal();
        } finally {
            LOCK.unlock();
        }
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
            // TODO: 2020/10/28 warn exception
            return "";
        }
    }
}
