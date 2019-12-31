package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 背包
 */
public class Bag<Item> implements Iterable<Item> {

    private List<Item> items;


    public Bag() {
        items = new ArrayList<>();
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    int size() {
        return items.size();
    }

    void add(Item item) {
        items.add(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
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
}
