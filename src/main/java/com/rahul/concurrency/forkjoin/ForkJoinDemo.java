package com.rahul.concurrency.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {
    public static void main(String[] args) {
        List<Executable<Integer>> executables = new ArrayList<>();
//        List<String> input = Arrays.asList("1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a", "10a");
//        List<String> input = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
//        for (String str : input) {
        for (int idx = 1; idx < 800; idx++) {
            String str = idx + "";
            executables.add(() -> {
                try {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        int j = i * i;
                    }
                    return Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    return -1;
                }
            });
        }

        long start = System.currentTimeMillis();
        List<Integer> output = new ForkJoin<Integer>().addAll(executables).invoke();
        long end = System.currentTimeMillis();
        System.out.println(output);

    }
}
