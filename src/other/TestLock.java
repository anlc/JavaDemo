package other;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestLock {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3,
            10,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public void printLog(Runnable runnable) {
        executor.execute(runnable);

    }

    public static void main(String[] args) {
        TestLock testLock = new TestLock();

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            testLock.printLog(new Runnable() {
                @Override
                public void run() {
                    System.out.println( Thread.currentThread().getName() + ", item: " + finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ",item == : " + finalI);
                }
            });
        }

    }
}
