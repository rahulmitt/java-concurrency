package com.rahul.concurrency.features.f1;

import com.rahul.concurrency.util.ThreadUtility;

public class ThreadsDemo {
    public static void main(String[] args) {
        new Thread(
                () -> System.out.println("Hello World from thread: [" + Thread.currentThread().getName() + "]")
        ).start();

        Thread t1 = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("********** I am not interrupted yet: [" + Thread.currentThread().getName() + "]");
                    }
                    System.out.println("interrupted! [" + Thread.currentThread().getName() + "]");
                }
        );

        t1.start();
        ThreadUtility.sleep(50);
        t1.interrupt();

        ThreadUtility.sleep(1000);
        System.out.println("Exiting main thread: [" + Thread.currentThread().getName() + "]");
    }
}
