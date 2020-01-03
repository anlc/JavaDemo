package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Stack<Item> implements Iterable<Item> {

    private java.util.Stack<Item> items;

    public Stack() {
        items = new java.util.Stack<>();
    }

    void push(Item item) {
        items.push(item);
    }

    Item pop() {
        return items.pop();
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
}
