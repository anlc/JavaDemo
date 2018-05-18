package datastructure;

import java.util.Random;

/**
 * 堆
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032660.html
 * <p>
 * 用数组实现的二叉树，假设节点的索引值为index，那么：
 * 　节点的左子节点是 2*index+1，
 * 　节点的右子节点是 2*index+2，
 * 　节点的父节点是 （index-1）/2。
 */
public class Heap {

    Node[] heapArray;
    int maxSize;
    int currentSize;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    boolean isEmpty() {
        return currentSize == 0;
    }

    boolean isFull() {
        return currentSize == maxSize;
    }

    boolean insert(int value) {
        if (isFull()) return false;

        Node newNode = new Node(value);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    //向上调整
    void trickleUp(int index) {
        int parentIndex = (index - 1);//父节点的索引
        Node bottom = heapArray[index];//将新加的尾节点存在bottom中
        while (index > 0 && heapArray[parentIndex].data < bottom.data) {
            heapArray[index] = heapArray[parentIndex];
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    Node remove() {
        if (isEmpty()) return null;
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    //向下调整
    void trickleDown(int index) {
        Node top = heapArray[index];
        int largeChildIndex;
        while (index < currentSize / 2) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < currentSize && heapArray[leftChildIndex].data < heapArray[rightChildIndex].data) {
                largeChildIndex = rightChildIndex;
            } else {
                largeChildIndex = leftChildIndex;
            }
            if (top.data >= heapArray[largeChildIndex].data) {
                break;
            }
            heapArray[index] = heapArray[largeChildIndex];
            index = largeChildIndex;
        }
        heapArray[index] = top;
    }

    //根据索引改变堆中某个数据
    boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize) {
            return false;
        }

        int oldValue = heapArray[index].data;
        heapArray[index].data = newValue;
        if (oldValue < newValue) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }
        return true;
    }

    void display() {
        System.out.println("heapArray(array format): ");
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null) {
                System.out.print(heapArray[i].data + "  ");
            } else {
                System.out.println("--");
            }
        }
    }

    class Node {
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        final int max = 30;
        Heap heap = new Heap(max);
        Random random = new Random(47);
        for (int i = 0; i < max; i++) {
            heap.insert(random.nextInt(100));
        }
        heap.display();
        System.out.println();
        heap.change(3, 80);
        heap.display();
        System.out.println();
        heap.remove();
        heap.display();
    }
}
