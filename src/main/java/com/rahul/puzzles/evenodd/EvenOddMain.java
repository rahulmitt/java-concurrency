package com.rahul.puzzles.evenodd;

public class EvenOddMain {
    public static void main(String... args) {
        Printer print = new Printer();
        new Thread(new EvenOddTask(print, false)).start();
        new Thread(new EvenOddTask(print, true)).start();
    }
}
