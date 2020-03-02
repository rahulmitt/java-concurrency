package com.rahul.puzzles.evenodd;

public class Printer {
    boolean odd = false;

    synchronized void printEven(int number) {
        try {
            while (!odd) wait();
            System.out.println(Thread.currentThread().getName() + " :: Even: " + number);
            odd = false;
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void printOdd(int number) {
        try {
            while (odd) wait();
            System.out.println(Thread.currentThread().getName() + " :: Odd: " + number);
            odd = true;
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
