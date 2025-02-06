package org.multithreading.samplethreadprograms;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class Task implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is executing the task.");
    }
}
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ExecutorService executor1 = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 5; i++) {
            executor.execute(new Task());
            executor1.execute(new Task());
        }
        executor.shutdown();
        executor1.shutdown();
    }
}
