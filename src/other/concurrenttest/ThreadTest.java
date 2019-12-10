package other.concurrenttest;

public class ThreadTest {

    public void testMethod(){
        synchronized (ThreadTest.class) {
            System.out.println(Thread.currentThread().getName() + " 开始同步 ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 结束同步 ");
        }

        System.out.println(Thread.currentThread().getName() + " 方法开始等待 ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 方法结束 ");
    }

    public static void main(String[] args) {

        final ThreadTest threadTest = new ThreadTest();

        threadTest.testMethod();

        new Thread(() -> threadTest.testMethod()).start();
        new Thread(() -> threadTest.testMethod()).start();
        new Thread(() -> threadTest.testMethod()).start();

        if (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
