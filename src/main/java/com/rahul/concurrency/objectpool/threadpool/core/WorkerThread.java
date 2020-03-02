package com.rahul.concurrency.objectpool.threadpool.core;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {
    private boolean alive = true;
    private BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    public void shutdown() {
        alive = false;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                Runnable r = queue.take();
                r.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
