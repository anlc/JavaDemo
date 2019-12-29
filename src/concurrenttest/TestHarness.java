package concurrenttest;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 */
public class TestHarness {

    public long timeTasks(int nThread, Runnable runnable) throws InterruptedException {
        System.out.println(" call timeTasks");

        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            System.out.println(" for ---- index:" + i);
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(" new Thread --- " + finalI);
                    startGate.await();
                    try {
                        runnable.run();
                    } finally {
                        System.out.println(" end gate countDown --- " + finalI);
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println(" start gate start count down --- ");
        long start = System.nanoTime();
        startGate.countDown();
        System.out.println(" start gate count down complete --- ");

        endGate.await();
        long end = System.nanoTime();
        System.out.println("method complete");
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        long time = new TestHarness().timeTasks(10, () -> System.out.println(Thread.currentThread().getName() + " --- run "));
        System.out.println("time: " + time);
    }

}
