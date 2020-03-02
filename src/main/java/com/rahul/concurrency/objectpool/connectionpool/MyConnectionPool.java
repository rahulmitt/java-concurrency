package com.rahul.concurrency.objectpool.connectionpool;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyConnectionPool {
    private BlockingQueue<Connection> queue;

    public MyConnectionPool(int poolSize) {
        queue = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            queue.add(createConnection());
        }
    }

    private Connection createConnection() {
        //TODO:
        return null;
    }

    public Connection getConnection() throws InterruptedException {
        return queue.take();        // alternatively, call poll() to avoid indefinite wait when underflow
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        queue.put(connection);      // alternatively, call offer() to avoid indefinite wait when overflow
    }
}
