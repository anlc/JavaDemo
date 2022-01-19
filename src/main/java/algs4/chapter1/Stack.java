package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Stack<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int size;

    public Stack() {
    }

    void push(Item item) {
        Node<Item> oldNode = first;
        first = new Node<>(item, oldNode);
        size++;
    }

    Item pop() {
        Item item = first.data;
        first = first.next;
        size--;
        return item;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Node<Item> current = first;

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
        // (1+((2*3)*(4*5)))
        Stack<String> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();
        List<String> opsArray = Arrays.asList("+", "-", "*", "/");

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) {
            } else if (opsArray.contains(s)) { // 忽略
                ops.push(s);
            } else if (s.equals(")")) { // 开始计算第一个值和第二值的运算
                Integer temp = vals.pop();
                String pop = ops.pop();
                int result = 0;
                if (pop.equals("+")) result = vals.pop() + temp;
                if (pop.equals("-")) result = vals.pop() - temp;
                if (pop.equals("*")) result = vals.pop() * temp;
                if (pop.equals("/")) result = vals.pop() / temp;
                vals.push(result);
            } else {
                vals.push(Integer.valueOf(s)); // 将值压入栈
            }
        }
        StdOut.println(vals.pop());
    }

    @Test
    public void testStack() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("item 1");
        stringStack.push("item 2");
        stringStack.pop();
        stringStack.push("item 3");
        for (String string : stringStack) {
            StdOut.println(string);
        }
    }
}
