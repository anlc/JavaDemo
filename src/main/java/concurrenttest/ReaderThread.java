package concurrenttest;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

public class ReaderThread extends Thread {

    interface CancelableTask<T> extends Callable<T> {
        void cancel();

        RunnableFuture<T> newTask();
    }

    static class CancellingExecutor extends ThreadPoolExecutor {

        public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            if (callable instanceof CancelableTask) {
                return ((CancelableTask<T>) callable).newTask();
            }
            return super.newTaskFor(callable);
        }
    }

    public abstract class SocketUsingTask<T> implements CancelableTask<T> {
        private Socket socket;

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public synchronized void cancel() {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public RunnableFuture<T> newTask() {
            return new FutureTask<T>(this) {
                @Override
                public boolean cancel(boolean mayInterruptIfRunning) {
                    SocketUsingTask.this.cancel();
                    return super.cancel(mayInterruptIfRunning);
                }
            };
        }
    }


    public static void main(String[] args) {
        CancellingExecutor executor = new CancellingExecutor(10, 10, 5, TimeUnit.DAYS, new LinkedBlockingDeque<>());

    }
}
