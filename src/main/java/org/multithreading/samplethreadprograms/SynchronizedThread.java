package org.multithreading.samplethreadprograms;

class Counter {
    private int counter = 0;

    public synchronized void increment() {
        System.out.println(Thread.currentThread().getName());
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

public class SynchronizedThread {
    public static void main(String args[]) {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Final count " + counter.getCounter());
    }
}
