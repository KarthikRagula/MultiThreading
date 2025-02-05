package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SynchronizationAllTypesTest {

    SynchronizationAllTypes obj = new SynchronizationAllTypes();

    @Test
    void calculateforNonStaticmethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            service.submit(obj::calculate);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MILLISECONDS);
        assertEquals(obj.getSum(), 10000);
    }

    @Test
    void calculateforStaticmethod() throws InterruptedException {
        ExecutorService service1 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            service1.submit(SynchronizationAllTypes::counter);
        }
        service1.shutdown();
        service1.awaitTermination(10, TimeUnit.MILLISECONDS);
        assertEquals(SynchronizationAllTypes.getCnt(), 10000);
    }

    @Test
    void testforSynchronizedBlock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(obj::listAdder);
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MILLISECONDS);
        assertEquals(obj.getList().size(),1000);
    }
}