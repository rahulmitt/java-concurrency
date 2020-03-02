package com.rahul.concurrency.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Task<T> extends RecursiveTask<List<T>> {

    private List<Executable<T>> tasks;
    private static final int TOTAL_CPU;

    private int depth;

    static {
        TOTAL_CPU = Runtime.getRuntime().availableProcessors();
    }

    public Task(List<Executable<T>> tasks) {
        this(0, tasks);
    }

    private Task(int depth, List<Executable<T>> tasks) {
        this.depth = depth;
        this.tasks = tasks;
    }

    private List<T> execute() {
        List<T> result = new ArrayList<>();
        for (Executable<T> executable : tasks) {
            result.add(executable.execute());
        }
        return result;
    }

    @Override
    protected List<T> compute() {
        List<T> result = new ArrayList<>();
        if ((int) Math.pow(2, depth) == TOTAL_CPU) {
            result.addAll((List<T>) execute());
        } else {
            int mid = tasks.size() / 2;
            Task<T> left = new Task<>(depth + 1, tasks.subList(0, mid));
            Task<T> right = new Task<>(depth + 1, tasks.subList(mid, tasks.size()));
            left.fork();
            right.fork();
            result.addAll((List<T>) left.join());
            result.addAll((List<T>) right.join());
        }
        return result;
    }
}
