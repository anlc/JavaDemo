package thread_demo;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    public void handlerData(){
        ThreadHelper.startTask(new ThreadHelper.Task() {
            @Override
            protected void run() {
                System.out.println("task handler data");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            @Override
            protected void onMainThread() {
                System.out.println("main handler data");
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        new InterruptDemo().handlerData();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("cancel task");
        ThreadHelper.cancel();
        TimeUnit.SECONDS.sleep(8);
        ThreadHelper.startTask(new ThreadHelper.Task() {
            @Override
            protected void run() {
                System.out.println("new task");
            }
        });
    }
}
