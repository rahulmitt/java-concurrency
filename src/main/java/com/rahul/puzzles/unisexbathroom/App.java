package com.rahul.puzzles.unisexbathroom;

public class App {
    public static void main(String[] args) {
        Resource r = new Resource(3);
        for (int i = 0; i < 10; i++) {
            Thread client = new Thread(new Client(r));
            client.start();
        }
    }
}
