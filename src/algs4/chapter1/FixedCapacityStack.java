package algs4.chapter1;


import java.util.Arrays;
import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {

    private Item[] items;
    private int n;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity) {
        this.items = (Item[]) new Object[capacity];
    }

    public void push(Item item) {
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;
    }

    public Item pop() {
        Item item = items[--n];
        items[n] = null;
        if (n > 0 && n < items.length / 4) resize(items.length / 2);
        return item;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int maxSize) {
        Item[] newArray = (Item[]) new Object[maxSize];
        for (int i = 0; i < n; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public String toString() {
        return "FixedCapacityStack{" +
                "size=" + n +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    class StackIterator implements Iterator<Item> {

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return items[--i];
        }
    }


    public static void testResize() {
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(4);
        for (int i = 0; i < 10; i++) {
            stack.push("item" + i);
            System.out.println("size:" + stack.size() + "  " + stack.toString());
        }

        System.out.println();

        for (int i = stack.size(); i > 0; i--) {
            stack.pop();
            System.out.println("size:" + stack.size() + "  " + stack.toString());
        }
    }

    public static void main(String[] args) {
//        FixedCapacityStack<String> stack = new FixedCapacityStack<>(4);
//
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) {
//                stack.push(item);
//            } else if (!stack.isEmpty()) {
//                StdOut.print(stack.pop() + " ");
//            }
//        }
//
//        StdOut.println("(" + stack.size() + " left on stack");

        testResize();
    }
}
