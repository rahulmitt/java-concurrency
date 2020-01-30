package com.rahul.concurrency.features.f1;

import com.rahul.concurrency.util.ThreadUtility;

public class ThreadsDemo {
    public static void main(String[] args) {
        new Thread(
                () -> ThreadUtility.print("Hello World from thread")
        ).start();

        Thread t1 = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        ThreadUtility.print("********** I am not interrupted yet");
                    }

                    ThreadUtility.print("interrupted!");
                }
        );

        t1.start();
        ThreadUtility.sleep(50);
        t1.interrupt();

        ThreadUtility.sleep(1000);
        ThreadUtility.print("Exiting main thread");
    }
}
