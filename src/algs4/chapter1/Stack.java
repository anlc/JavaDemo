package algs4.chapter1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Stack<Item> implements Iterable<Item> {

    private Queue<Item> items;

    public Stack() {
        items = new LinkedList<>();
    }

    void put(Item item) {
        items.add(item);
    }

    Item pop() {
        return items.poll();
    }

    int size() {
        return items.size();
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    public static void main(String[] args) {

    }
}
