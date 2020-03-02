package com.rahul.concurrency.producerconsumer.using_primitive_synchronization;

import com.rahul.concurrency.util.ThreadUtility;

import java.util.Random;

public class Producer implements Runnable {
    private Buffer b;
    private Random random = new Random();

    public Producer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            ThreadUtility.sleep(2000);
            try {
                int data = random.nextInt(10);
                b.put(data);
                System.out.println("Enqueue: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
