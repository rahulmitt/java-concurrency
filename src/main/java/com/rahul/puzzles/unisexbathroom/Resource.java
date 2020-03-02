package com.rahul.puzzles.unisexbathroom;

public class Resource {
    private int numClients = 0;
    private final int maxClients;

    public Resource(int maxClients) {
        this.maxClients = maxClients;
    }

    public synchronized void acquire() {
        while (!clientCanAcquire()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        ++numClients;
        printState();
    }

    public synchronized void release() {
        --numClients;
        printState();
        notify();
    }

    private boolean clientCanAcquire() {
        return numClients < maxClients;
    }

    private void printState() {
        System.out.println("Resource is currently acquired by " + numClients + " clients");
    }
}
