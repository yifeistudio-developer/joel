package com.yifeistudio.joel.worker;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yi
 * @since 2020/10/27-5:28 下午
 */
public class ListenerTests {

    private int state;

    private static final Lock lock = new ReentrantLock();

    private static final Condition started = lock.newCondition();

    private static final Condition resume = lock.newCondition();


    @Test
    public void stateTest() {

    }

}
