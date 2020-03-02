package com.rahul.concurrency.producerconsumer.using_primitive_synchronization;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer b = new Buffer();
        new Thread(new Producer(b)).start();
        new Thread(new Producer(b)).start();

        new Thread(new Consumer(b)).start();
        new Thread(new Consumer(b)).start();
    }

}
