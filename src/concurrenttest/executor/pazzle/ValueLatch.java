package concurrenttest.executor.pazzle;

import java.util.concurrent.CountDownLatch;

class ValueLatch<T> {

    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    boolean isSet() {
        return done.getCount() == 0;
    }

    synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
