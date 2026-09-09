package threading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchPrac {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(3);
        CountDownLatch latch=new CountDownLatch(3);
        service.submit(new TestLatch(latch));
        service.submit(new TestLatch(latch));
        service.submit(new TestLatch(latch));

        latch.await();
        System.out.println("All dependent tasks completed");
    }
}
