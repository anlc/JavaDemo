package concurrenttest.thread_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadHelper {

    private Thread mainThread;
    private ExecutorService service;
    private Task currentTask;
    private Future serviceFuture;

    public static ThreadHelper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        public static final ThreadHelper instance = new ThreadHelper();
    }

    private ThreadHelper() {
        mainThread = new Thread("main") {
            @Override
            public void run() {
                System.out.println("---------start main Thread :" +Thread.currentThread().getName());
                currentTask.onMainThread();
                System.out.println("---------end main Thread :" +Thread.currentThread().getName());
            }
        };
        service = Executors.newCachedThreadPool();
    }

    private void startThread(final Task task) {
        this.currentTask = task;
        serviceFuture = service.submit(() -> {
            try {
                System.out.println("---------start task Thread :" + Thread.currentThread().getName());
                task.run();
                System.out.println("---------end task Thread :" + Thread.currentThread().getName());
                service.execute(mainThread);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void stopTask(){
        if (serviceFuture == null) {
            return;
        }
        System.out.println("cancel execute");
        serviceFuture.cancel(true);
        mainThread.interrupt();
    }

    public static void startTask(Task task) {
        getInstance().startThread(task);
    }

    public static void release(){
        getInstance().service.shutdown();
    }

    public static void cancel(){
        getInstance().stopTask();
    }

    public static abstract class Task {
        private boolean isCancel = false;

        protected abstract void run();

        protected void onMainThread() {
        }

        public final void cancel() {
            isCancel = true;
        }
    }

}