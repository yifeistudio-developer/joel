package com.yifeistudio.joel.worker;

import org.junit.Test;

import java.lang.management.ManagementFactory;

/**
 * @author yi
 * @since 2020/10/28-11:34 上午
 */
public class WorkerContextTests {


    @Test
    public void loadWorkerInfoTest() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        int coreSize = Runtime.getRuntime().availableProcessors();
        System.out.println(coreSize);

    }





}
