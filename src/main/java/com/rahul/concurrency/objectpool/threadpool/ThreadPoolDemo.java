package com.rahul.concurrency.objectpool.threadpool;

import com.rahul.concurrency.objectpool.threadpool.core.ThreadPool;
import com.rahul.concurrency.util.ThreadUtility;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + " :: started");
                            ThreadUtility.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + " :: completed");
                        }
                );
            }
        } finally {
            threadPool.shutdown();
        }
    }
}

