package com.rahul.concurrency.concurrentcollections.count_down_latch;

import com.rahul.concurrency.util.ThreadUtility;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private int i;
    private CountDownLatch latch;

    public Worker(int i, CountDownLatch latch) {
        this.i = i;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }

    private void doWork() {
        System.out.println("worker i=" + i + " started");
        ThreadUtility.sleep(1000);
    }
}
