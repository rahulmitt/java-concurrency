package com.rahul.concurrency.concurrentcollections.cyclic_barrier;

import com.rahul.concurrency.util.ThreadUtility;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            ThreadUtility.sleep(2000);
            System.out.println("Barrier Action executed");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i, barrier));
        }

        executorService.shutdown();
    }
}

