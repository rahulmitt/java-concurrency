package com.rahul.puzzles.unisexbathroom;

import java.util.Random;

public class Client implements Runnable {

    private Resource resource;

    public Client(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            resource.acquire();
            Thread.sleep(1000);
            resource.release();
        } catch (InterruptedException e) {
        }
    }
}
