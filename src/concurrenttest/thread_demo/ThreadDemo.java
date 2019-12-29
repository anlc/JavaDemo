package concurrenttest.thread_demo;

public class ThreadDemo {


    public synchronized void testMethod() {
        boolean a = false;
        System.out.println(a);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = true;
        System.out.println(a);
    }

    public static void main(String[] args) {
        final ThreadDemo demo = new ThreadDemo();
        new Thread(() -> {
            System.out.println("111");
            demo.testMethod();
        }).start();
        new Thread(() -> {
            System.out.println("222");
            demo.testMethod();
        }).start();

    }
}
