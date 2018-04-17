package datastructure;

import org.junit.Test;

/**
 * 有序链表
 *
 * 参考：https://www.cnblogs.com/ysocean/p/7928988.html#_label0
 */
public class OrderLinkedList {

    Node first;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    //插入节点，并按照从小打到的顺序排列
    void insert(int data) {
        Node node = new Node(data);
        Node pre = null;
        Node current = first;
        while (current != null && data > current.data) {
            pre = current;
            current = current.next;
        }
        if (pre == null) {
            first = node;
            first.next = current;
        } else {
            pre.next = node;
            node.next = current;
        }
    }

    //删除头节点
    void deleteFirst() {
        first = first.next;
    }

    void display() {
        Node node = first;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    @Test
    public void testLink() {
        OrderLinkedList list = new OrderLinkedList();

        list.insert(5);
        list.insert(3);
        list.insert(2);
        list.insert(4);
        list.insert(1);
        list.display();
        System.out.println();
        list.deleteFirst();
        list.display();
    }
}
