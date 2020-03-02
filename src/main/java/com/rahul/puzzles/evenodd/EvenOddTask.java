package com.rahul.puzzles.evenodd;

import com.rahul.concurrency.util.ThreadUtility;

public class EvenOddTask implements Runnable {
    private Printer print;
    private boolean isEvenNumber;

    public EvenOddTask(Printer print, boolean isEvenNumber) {
        this.print = print;
        this.isEvenNumber = isEvenNumber;
    }

    @Override
    public void run() {
        int number = isEvenNumber ? 2 : 1;
        while (true) {
            if (isEvenNumber) print.printEven(number);
            else print.printOdd(number);
            number += 2;
            ThreadUtility.sleep(1000);
        }

    }
}
