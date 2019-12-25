package other.concurrenttest;

import java.util.concurrent.*;

public class FutureCancel {

    private final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public void timedRun2(Runnable r, long timeout, TimeUnit unit) throws ExecutionException {
        Future<?> submit = cancelExec.submit(r);

        try {
            submit.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw e;
        } finally {
            submit.cancel(true);
        }
    }

    public void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {

            private volatile Throwable throwable;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable throwable) {
                    this.throwable = throwable;
                }
            }

            void rethrow() throws Exception {
                if (throwable != null) {
                    throw new Exception(throwable);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        Thread thread = new Thread(task);
        thread.start();
        cancelExec.schedule(() -> thread.interrupt(), timeout, unit);
        thread.join(unit.toMillis(timeout));
    }
}
