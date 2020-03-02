package com.rahul.concurrency.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ForkJoin<T> {
    private ForkJoinPool pool;
    private List<Executable<T>> executables;

    public ForkJoin() {
        pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        executables = new ArrayList<>();
    }

    public ForkJoin<T> add(Executable<T> executable) {
        executables.add(executable);
        return this;
    }

    public ForkJoin<T> addAll(List<Executable<T>> executables) {
        this.executables.addAll(executables);
        return this;
    }

    public List<T> invoke() {
        return pool.invoke(new Task<T>(executables));
    }
}
