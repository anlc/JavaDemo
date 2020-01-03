package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<Item> implements Iterable<Item> {

    private java.util.Queue<Item> queue;

    public Queue() {
        queue = new LinkedList<>();
    }

    void enqueue(Item item) {
        queue.add(item);
    }

    Item dequeue() {
        return queue.poll();
    }

    int size() {
        return queue.size();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public Iterator<Item> iterator() {
        return queue.iterator();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readInt());
        }

        int n = queue.size();
        for (int i = 0; i < n; i++) { // size 要提前获取，dequeue会使元素出队列，改变队列的大小
            StdOut.printf("%d ", queue.dequeue());
        }
    }
}
