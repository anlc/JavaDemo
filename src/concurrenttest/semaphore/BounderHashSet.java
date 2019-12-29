package concurrenttest.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 */
public class BounderHashSet<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    public BounderHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        semaphore.acquire(); // 会引起阻塞，当acquire累加的值等于bound后，会阻塞
        boolean wasAdded = false;
        try {
            System.out.println("add ---- " + Thread.currentThread().getName());
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            System.out.println("add ---- " + wasAdded);
            if (!wasAdded) { // 当添加失败时，释放一个acquire获得的许可(prime)
                semaphore.release();
            }
        }
    }

    public boolean remove(T o) {
        System.out.println("remove ---- " + Thread.currentThread().getName());
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) {
        BounderHashSet<String> stringBounderHashSet = new BounderHashSet<>(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            System.out.println("for add ---- " + i);
            new Thread(() -> {
                try {
                    stringBounderHashSet.add("item " + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            System.out.println("for remove ---- " + i);
            new Thread(() -> stringBounderHashSet.remove("item " + finalI)).start();
        }
        System.out.println(stringBounderHashSet.set);
    }
}
