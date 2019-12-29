package concurrenttest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.*;

public class Memoizer<A, V> implements Computable<A, V> {

    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memoizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A a) throws InterruptedException {
        while (true) {
            Future<V> vFuture = cache.get(a);
            if (vFuture == null) {
                FutureTask<V> task = new FutureTask<>(() -> computable.compute(a));
                vFuture = cache.putIfAbsent(a, task);
                if (vFuture == null) {
                    vFuture = task;
                    task.run();
                }
            }
            try {
                return vFuture.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BigInteger[] cache = new BigInteger[10];
        final Memoizer<BigInteger, BigInteger[]> memoizer = new Memoizer<>(bigInteger -> factor(bigInteger, cache));
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    memoizer.compute(new BigInteger("10"));
                    memoizer.compute(new BigInteger("10"));
                    memoizer.compute(new BigInteger("11"));
                    memoizer.compute(new BigInteger("12"));
                    memoizer.compute(new BigInteger("13"));
                    memoizer.compute(new BigInteger("11"));
                    memoizer.compute(new BigInteger("12"));
                    memoizer.compute(new BigInteger("13"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println(Arrays.toString(memoizer.compute(new BigInteger("13"))));
    }

    private static BigInteger[] factor(BigInteger integer, BigInteger[] cache) {
        for (int i = 0; i < cache.length; i++) {
            if (cache[i] == null) {
                cache[i] = integer;
                break;
            }
        }
        return cache;
    }
}

interface Computable<A, V> {
    V compute(A a) throws InterruptedException;
}
