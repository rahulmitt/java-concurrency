package com.rahul.concurrency.features.f2;

import com.rahul.concurrency.util.ThreadUtility;

/*
    SAMPLE OUTPUT 1:
        Entered instanceMethod3() : state=0 :: [Thread-2]
        Entered instanceMethod1() : state=0 :: [Thread-0]
        ********** Exited instanceMethod1() : state=1 :: [Thread-0]
        ********** Exited instanceMethod3() : state=1 :: [Thread-2]
        Entered instanceMethod2() : state=1 :: [Thread-1]
        ********** Exited instanceMethod2() : state=2 :: [Thread-1]
        Exiting main thread :: [main]

    SAMPLE OUTPUT 1:
        Entered instanceMethod1() : state=0 :: [Thread-0]
        Entered instanceMethod3() : state=0 :: [Thread-2]
        ********** Exited instanceMethod1() : state=3 :: [Thread-0]
        ********** Exited instanceMethod3() : state=3 :: [Thread-2]
        Entered instanceMethod2() : state=3 :: [Thread-1]
        ********** Exited instanceMethod2() : state=2 :: [Thread-1]
        Exiting main thread :: [main]
*/
public class ObjectLevelLocking {

    public static void main(String[] args) {
        SharedState1 sharedState = new SharedState1();
        new Thread(() -> sharedState.instanceMethod1()).start();
        new Thread(() -> sharedState.instanceMethod2()).start();
        new Thread(() -> sharedState.instanceMethod3()).start();

        ThreadUtility.sleep(3000);
        ThreadUtility.print("Exiting main thread");
    }
}

/*
    instanceMethod1() and instanceMethod2() are synchronized on the same OBJECT-LEVEL lock
    whereas, instanceMethod3() is synchronized on a different OBJECT-LEVEL lock

    This means that:
        1. instanceMethod1() and instanceMethod2() are mutually exclusive
        2. instanceMethod1() and instanceMethod3() is not mutually exclusive
*/
class SharedState1 {
    private static int state = 0;

    public synchronized void instanceMethod1() {
        ThreadUtility.print("Entered instanceMethod1() : state=" + state);
        state = 1;
        ThreadUtility.sleep(500);
        ThreadUtility.print("********** Exited instanceMethod1() : state=" + state);
    }

    public void instanceMethod2() {
        synchronized (this) {
            ThreadUtility.print("Entered instanceMethod2() : state=" + state);
            state = 2;
            ThreadUtility.sleep(500);
            ThreadUtility.print("********** Exited instanceMethod2() : state=" + state);
        }
    }

    private final Object lock = new Object();

    public void instanceMethod3() {
        synchronized (lock) {
            ThreadUtility.print("Entered instanceMethod3() : state=" + state);
            state = 3;
            ThreadUtility.sleep(500);
            ThreadUtility.print("********** Exited instanceMethod3() : state=" + state);
        }
    }
}