package datastructure.graph;

/**
 * 广度优先搜索的队列
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032659.html
 */
public class Queue {
    private final int SIZE = 20;
    private int[] array;
    private int front;
    private int rear;

    public Queue() {
        array = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int i) {
        if (rear == SIZE - 1) {
            rear = -1;
        }
        array[++rear] = i;
    }

    public int remove() {
        int temp = array[front++];
        if (front == SIZE) {
            front = 0;
        }
        return temp;
    }

    public boolean isEmpty() {
        return rear + 1 == front || front + SIZE - 1 == rear;
    }
}
