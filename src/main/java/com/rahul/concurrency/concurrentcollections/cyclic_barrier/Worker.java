package com.rahul.concurrency.concurrentcollections.cyclic_barrier;

import com.rahul.concurrency.util.ThreadUtility;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
    private int id;
    private CyclicBarrier barrier;

    public Worker(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
        try {
            barrier.await();
            System.out.println("After await()");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        System.out.println("Worker with id=" + id + " starts");
        ThreadUtility.sleep(5000);
        System.out.println("Worker with id=" + id + " finished");
    }
}
