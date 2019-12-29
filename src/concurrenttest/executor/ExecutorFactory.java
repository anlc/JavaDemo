package concurrenttest.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by n on 2017/11/20.
 */
public class ExecutorFactory {

    public static ExecutorService create(String type) {
        ExecutorService service = null;
        switch (type) {
            case "cache":
                service = Executors.newCachedThreadPool();
                break;
            case "fixed"://注意：这里创建的线程池限定最大是2
                service = Executors.newFixedThreadPool(2);
                break;
            case "single":
                service = Executors.newSingleThreadExecutor();
                break;
        }
        return service;
    }
}
