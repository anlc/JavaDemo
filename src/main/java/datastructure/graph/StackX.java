package datastructure.graph;

/**
 * 深度优先搜索的栈
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032659.html
 */
public class StackX {

    private final int SIZE = 20;
    private int[] array;
    private int top;

    public StackX() {
        top = -1;
        array = new int[SIZE];
    }

    public void push(int i) {
        array[++top] = i;
    }

    public int pop() {
        return array[top--];
    }

    public int peek() {
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
