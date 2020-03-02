package com.rahul.concurrency.producerconsumer.using_primitive_synchronization;

import com.rahul.concurrency.util.ThreadUtility;

public class Consumer implements Runnable {
    private Buffer b;

    public Consumer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            ThreadUtility.sleep(2000);
            try {
                Integer data = b.get();
                System.out.println("\t\tDEQUEUE: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
