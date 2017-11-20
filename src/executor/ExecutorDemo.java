package executor;

import java.util.concurrent.ExecutorService;

/**
 * Created by n on 2017/11/20.
 */
public class ExecutorDemo {

    private ExecutorService service;

    public ExecutorDemo(String type) {
        service = ExecutorFactory.create(type);
    }

    public void submit(RunnableClass runnable) {
        service.submit(runnable);
    }

    public void execute(RunnableClass runnable) {
        service.execute(runnable);
    }

    public void release() {
        service.shutdown();
    }

}
