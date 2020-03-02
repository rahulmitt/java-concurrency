package com.rahul.puzzles.firstsecondthird;

import com.rahul.concurrency.util.ThreadUtility;

public class Task implements Runnable {
    private static volatile int counter = 0;
    private int n;
    private int i;

    private static final Object lock = new Object();

    public Task(int n, int i) {
        this.n = n;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (true) {
                    while (counter % n != i) lock.wait();
                    System.out.println(Thread.currentThread().getName() + " :: " + counter);
                    counter++;
                    ThreadUtility.sleep(2000);
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
