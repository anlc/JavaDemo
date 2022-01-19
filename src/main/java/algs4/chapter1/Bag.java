package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Iterator;

/**
 * 背包
 */
public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int size;

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    void add(Item item) {
        Node<Item> oldNode = this.first;
        Node<Item> newNode = new Node<>(item, oldNode);
        first = newNode;
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


    class Node<Item> {
        Item data;
        Node<Item> next;

        public Node(Item data, Node<Item> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty()) { // ctrl + d
            numbers.add(StdIn.readDouble());
        }

        int n = numbers.size();

        double sum = 0.0;
        for (double d : numbers) {
            sum += d;
        }
        double mean = sum / n;

        sum = 0.0;
        for (double d : numbers) {
            // 每个值和平均值之差的平方和
            sum += (d - mean) * (d - mean);
        }
        double std /* 标准差 */ = Math.sqrt(sum / (n - 1)) /* 除以N-1之后的平方根 */;

        //标准差 = 每个值和平均值之差的平方之和除以N-1之后的平方根

        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("std dev: %.2f\n", std);
    }

    @Test
    public void testBag() {
        Bag<String> bag = new Bag<>();
        bag.add("item 1");
        bag.add("item 2");
        bag.add("item 3");

        for (String string : bag) {
            StdOut.println(string);
        }
    }
}
