package com.rahul.puzzles.firstsecondthird;

public class Main {
    public static void main(String[] args) {
        int nThreads = 3;
        for (int i = 0; i < nThreads; i++) {
            new Thread(new Task(nThreads, i)).start();
        }
    }
}
