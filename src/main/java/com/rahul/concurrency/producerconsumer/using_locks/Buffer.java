package com.rahul.concurrency.producerconsumer.using_locks;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private static final int MAX_SIZE = 10;
    List<Integer> queue = new LinkedList<>();

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void put(int data) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == MAX_SIZE) condition.await();
            queue.add(data);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Integer get() throws InterruptedException {
        lock.lock();
        int data;
        try {
            while (queue.size() == 0) condition.await();
            data = queue.remove(0);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
        return data;
    }
}
