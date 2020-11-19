package concurrenttest.thread_demo;

import java.util.concurrent.TimeUnit;

public class JoinDemo {

    public static void main(String[] args) {
        Thread prevThread = new Thread(() -> {
            System.out.println("prev thread run -- ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("prev thread complete -- ");
        });

        Thread joinThread = new Thread(() -> {
            try {
                System.out.println("join thread run --");
                // 阻塞，等到上一线程执行完成再执行。注释这句话会先打印prev的log信息
                prevThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("join thread complete --");
        });

        prevThread.start();
        joinThread.start();
    }
}
