package executor;

import java.util.concurrent.TimeUnit;

/**
 * Created by n on 2017/11/20.
 */
public class RunnableClass implements Runnable {

    private static int taskCount = 0;
    private final int id = taskCount++;

    @Override
    public void run() {
        System.out.println("id:" + id + "开始");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("id:" + id + ",完成");
    }
}
