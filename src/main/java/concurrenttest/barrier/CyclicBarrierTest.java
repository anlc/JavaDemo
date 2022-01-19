package concurrenttest.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private final CyclicBarrier barrier;

    public CyclicBarrierTest() {
        // 初始化
        barrier = new CyclicBarrier(3);
//        barrier = new CyclicBarrier(3, () -> System.out.println("栅栏被打开时，执行的操作，可选参数"));
    }

    public void testMethod(int index) throws BrokenBarrierException, InterruptedException {
        System.out.println("call testMethod :" + index);
        // 每等待三个线程到达，执行一次
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
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
