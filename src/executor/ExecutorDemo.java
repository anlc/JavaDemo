package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by n on 2017/11/20.
 */
public class ExecutorDemo {

    private ExecutorService service;

    public ExecutorDemo(String type) {
        service = ExecutorFactory.create(type);
    }

    public Future<?> submit(RunnableClass runnable) {
        return service.submit(runnable);
    }

    public void execute(RunnableClass runnable) {
        service.execute(runnable);
    }

    public void release() {
        service.shutdown();
    }

}
