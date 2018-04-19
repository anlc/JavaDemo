package datastructure.linkedlist;

import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * 双端列表
 * 参考：LinkedList
 */
public class DoubleLinkedList<T> {

    int size;
    Node first; //第一个
    Node last;//最后一个

    private class Node {
        Node prev;//上一个
        Node next;//下一个
        T data;

        public Node(Node prev, Node next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    //在链表头添加
    public void addFirst(T data) {
        Node f = first;
        Node newNode = new Node(null, f, data);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;

        size++;
    }

    //在链表尾添加
    public void addLast(T data) {
        Node l = last;
        Node newNode = new Node(l, null, data);
        last = newNode;
        if (l == null)
            first = l;
        else
            l.next = newNode;
        size++;
    }

    //获取链表第一个元素
    public T getFirst() {
        Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.data;
    }

    //获取链表最后一个元素
    public T getLast() {
        Node l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.data;
    }

    //判断index是否在范围内
    public void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    //获取在index位置的元素
    public T get(int index) {
        checkElementIndex(index);
        return node(index).data;
    }

    //设置在index位置的元素值
    public T set(int index, T data) {
        checkElementIndex(index);
        Node node = node(index);
        T oldValue = node.data;
        node.data = data;
        return oldValue;
    }

    //获取在index位置的node
    Node node(int index) {
        if (index < (size >> 1)) {
            Node n = first;
            for (int i = 0; i < index; i++)
                n = n.next;
            return n;
        } else {
            Node n = last;
            for (int i = size - 1; i > index; i--)
                n = n.prev;
            return n;
        }
    }

    //删除在index位置的元素
    public T delete(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    T unlink(Node node) {
        T data = node.data;
        Node prev = node.prev;
        Node next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        size--;
        return data;
    }

    public void display() {
        System.out.print("[");
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                System.out.print(node.data + "->");
            } else {
                System.out.print(node.data);
            }
            node = node.next;
        }
        System.out.println("]");
    }

    @Test
    public void testLinked() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addFirst("D");
        list.addLast("E");
        list.addLast("F");
        list.addLast("H");
        list.addLast("I");

        list.delete(2);
        list.display();
        System.out.println(list.get(2));

        list.delete(3);
        list.display();

    }
}
