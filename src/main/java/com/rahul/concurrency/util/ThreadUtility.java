package com.rahul.concurrency.util;

public class ThreadUtility {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void print(String message) {
        System.out.println(String.format("%s :: [%s]", message, Thread.currentThread().getName()));
    }
}
