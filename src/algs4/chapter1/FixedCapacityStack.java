package algs4.chapter1;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> {

    private Item[] items;
    private int n;

    public FixedCapacityStack(int capacity) {
        this.items = (Item[]) new Object[capacity];
    }

    public void push(Item item) {
        items[n++] = item;
    }

    public Item pop() {
        return items[--n];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(100);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stack.push(item);
            } else if(!stack.isEmpty()){
                StdOut.print(stack.pop() + " ");
            }
        }

        StdOut.println("(" + stack.size() + " left on stack");
    }
}
