package com.rahul.concurrency.objectpool.threadpool.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool {
    private WorkerThread[] workerThreads;
    private BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();

    public ThreadPool(int size) {
        workerThreads = new WorkerThread[size];
        for (int i = 0; i < size; i++) {
            workerThreads[i] = new WorkerThread(queue);
            workerThreads[i].start();
        }
    }

    public void execute(Runnable r) {
        try {
            queue.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        while (queue.size() != 0) ;
        System.out.println("Shutting down thread-pool");
        for (int i = 0; i < workerThreads.length; i++) {
            workerThreads[i].shutdown();
        }
    }
}
