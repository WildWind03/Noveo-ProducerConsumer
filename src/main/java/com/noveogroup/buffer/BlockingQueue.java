package com.noveogroup.buffer;

import java.util.LinkedList;

public class BlockingQueue<T> implements Buffer{
    private final int maxSize;

    private final Object lock = new Object();
    private final LinkedList<T> insideQueue = new LinkedList<>();

    public BlockingQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Blocking queue: Invalid max size");
        }

        this.maxSize = maxSize;
    }

    public void put(T obj) throws InterruptedException {
        synchronized (lock) {
            while (insideQueue.size() >= maxSize) {
                lock.wait();
            }

            insideQueue.add(obj);
            lock.notifyAll();
        }
    }

    public T pop() throws InterruptedException {
        synchronized (lock) {
            while (insideQueue.isEmpty()) {
               lock.wait();
            }

            T current = insideQueue.pop();
            lock.notifyAll();
            return current;

        }
    }
}
