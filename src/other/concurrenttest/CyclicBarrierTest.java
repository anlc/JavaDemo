package other.concurrenttest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

    private final CyclicBarrier barrier;

    public CyclicBarrierTest() {
        barrier = new CyclicBarrier(3);
    }

    public void testMethod(int index) throws BrokenBarrierException, InterruptedException {
        System.out.println("call testMethod :" + index);
        barrier.await();
        System.out.println("complete : " + index);
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    cyclicBarrierTest.testMethod(finalI);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
