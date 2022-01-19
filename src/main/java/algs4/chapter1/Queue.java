package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    void enqueue(Item item) {
        Node<Item> oldNode = last;
        last = new Node<>(item, null);

        if (first == null) {
            first = last;
        } else {
            oldNode.next = last;
        }
        size++;
    }

    Item dequeue() {
        Item data = first.data;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        return data;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private class Node<Item> {
        Item data;
        Node<Item> next;

        public Node(Item data, Node<Item> next) {
            this.data = data;
            this.next = next;
        }
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

    @Test
    public void testQueue() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("item 1");
        queue.enqueue("item 2");
        queue.enqueue("item 3");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        for (String string : queue) {
            StdOut.print(string + "\t");
        }
    }
}
