package com.rahul.concurrency.producerconsumer.using_primitive_synchronization;

import java.util.LinkedList;
import java.util.List;

public class Buffer {
    private static final int MAX_SIZE = 10;
    private List<Integer> queue = new LinkedList<>();

    public synchronized void put(int data) throws InterruptedException {
        while (queue.size() == MAX_SIZE) wait();
        queue.add(data);
        notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        int data;
        while (queue.size() == 0) wait();
        data = queue.remove(0);
        notifyAll();
        return data;
    }
}
