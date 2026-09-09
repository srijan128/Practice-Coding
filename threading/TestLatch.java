package threading;

import java.util.concurrent.CountDownLatch;

public class TestLatch implements Runnable{
    private CountDownLatch latch;

    public TestLatch(CountDownLatch latch){
        this.latch=latch;
    }
    @Override
    public void run() {
       latch.countDown();
    }
}
