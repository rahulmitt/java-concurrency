package com.rahul.concurrency.producerconsumer.using_locks;

public class ProducerConsumerUsingLocks {
    public static void main(String[] args) {
        Buffer b = new Buffer();
        new Thread(new Producer(b)).start();
        new Thread(new Producer(b)).start();

        new Thread(new Consumer(b)).start();
    }
}
