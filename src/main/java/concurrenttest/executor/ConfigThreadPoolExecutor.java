package concurrenttest.executor;

import java.util.concurrent.*;

public class ConfigThreadPoolExecutor {

    public void testThreadPoolExecutor() {
        Executors.newFixedThreadPool(20);
        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(
                20,
                20,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>());

        Executors.newCachedThreadPool();
        ThreadPoolExecutor cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        Executors.newSingleThreadExecutor();
    }
}
