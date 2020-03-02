package com.rahul.concurrency.concurrentcollections.concurrent_hash_map;

public class CHMDemo {
    public static void main(String[] args) {
        MyConcurrentHashMap map = new MyConcurrentHashMap();

        map.put(5, 5);
        System.out.println("map.get(5) = " + map.get(5));

        map.put(128, 128);
        System.out.println("map.get(128) = " + map.get(128));
        map.put(0, 0);
        System.out.println("map.get(0) = " + map.get(0));
    }
}
