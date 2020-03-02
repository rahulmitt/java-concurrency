package com.rahul.concurrency.features.f3;

import com.rahul.concurrency.util.ThreadUtility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UsingReentrantLocks {
    public static void main(String[] args) {
        SharedState3 sharedState = new SharedState3();
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 1; i++) {
                threadPool.execute(() -> sharedState.instanceMethod1());
            }

            for (int i = 0; i < 1; i++) {
                threadPool.execute(() -> {
                            try {
                                sharedState.instanceMethod2();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        } finally {
            ThreadUtility.sleep(3000);
            ThreadUtility.print("Exiting");
            threadPool.shutdown();
        }
    }
}

class SharedState3 {
    /*
        FAIRNESS guarantee comes to play only when a lock is free and
        JAVA-THREAD-SCHEDULER has to decide which thread the lock should be given to.
        And it is given to longest waiting thread (in case of synchronized, it's random).

        Fairness of locks does not guarantee fairness of thread scheduling.
        This means that, the OS:
            - will schedule the thread to run whenever it likes.
            - has little idea what the JVM would like to run next.

        Fairness has nothing to do with thread priorities


        Fairness lock guarantees lack of STARVATION but one of many threads may obtain
        lock multiple times in succession while other threads are not making any progress.
        That it, it does not avoid obtaining lock again by thread which had recently released the same lock
    */
    private Lock lock = new ReentrantLock(true);
    private boolean state;

    public void instanceMethod1() {
        lock.lock();
        try {
            ThreadUtility.print("Acquired Lock");
            state = !state;
            ThreadUtility.sleep(200);
            ThreadUtility.print("State changed");
        } finally {
            lock.unlock();
        }
    }

    public void instanceMethod2() throws InterruptedException {

        /*
            Acquires the lock if it is not held by another thread and returns immediately with the value true
            Even when this lock has been set to use a fair ordering policy, a call to tryLock() will immediately
            acquire the lock if it is available, whether or not other threads are currently waiting for the lock
        */
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                ThreadUtility.print("Doing some work");
                ThreadUtility.sleep(500);
                ThreadUtility.print("Done");
            } finally {
                lock.unlock();
            }

        } else {
            ThreadUtility.print("Failed to acquire the lock");
        }
    }
}
