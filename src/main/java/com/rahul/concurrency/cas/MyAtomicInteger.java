package com.rahul.concurrency.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyAtomicInteger {
    private volatile int value = 0;

    /*
        http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
    */
    private Unsafe unsafe;
    private long offset;    // memory address of the "value" field

    public MyAtomicInteger() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        this.unsafe = (Unsafe) f.get(null);

        Field value = MyAtomicInteger.class.getDeclaredField("value");
        offset = unsafe.objectFieldOffset(value);
    }

    public MyAtomicInteger(int value) throws Exception {
        this();
        this.value = value;
    }

    // atomically update the value to 'next' if it is currently holding 'current' value
    public final boolean compareAndSwap(int current, int next) {
        return unsafe.compareAndSwapInt(this, offset, current, next);
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    public final int getAndSet(int next) {
        for (; ; ) {
            int current = get();
            if (compareAndSwap(current, next)) return current;
        }
    }

    public final int getAndAdd(int delta) {
        for (; ; ) {
            int current = get();
            int next = current + delta;
            if (compareAndSwap(current, next)) return current;
        }
    }

    public final int getAndIncrement() {
        for (; ; ) {
            int current = get();
            int next = current + 1;
            if (compareAndSwap(current, next)) return current;
        }
    }

    public final int getAndDecrement() {
        for (; ; ) {
            int current = get();
            int next = current - 1;
            if (compareAndSwap(current, next)) return current;
        }
    }

    //----------

    public final int setAndGet(int next) {
        for (; ; ) {
            int current = get();
            if (compareAndSwap(current, next)) return next;
        }
    }

    public final int addAndGet(int delta) {
        for (; ; ) {
            int current = get();
            int next = current + delta;
            if (compareAndSwap(current, next)) return next;
        }
    }

    public final int incrementAndGet() {
        for (; ; ) {
            int current = get();
            int next = current + 1;
            if (compareAndSwap(current, next)) return next;
        }
    }

    public final int decrementAndGet() {
        for (; ; ) {
            int current = get();
            int next = current - 1;
            if (compareAndSwap(current, next)) return next;
        }
    }

    public static void main(String[] args) throws Exception {
        MyAtomicInteger myAtomicInteger = new MyAtomicInteger();
        System.out.println(myAtomicInteger.get());
        System.out.println(myAtomicInteger.getAndIncrement());
        System.out.println(myAtomicInteger.incrementAndGet());
    }
}
