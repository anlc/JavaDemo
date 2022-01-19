package datastructure.linkedlist;

import org.junit.Test;

/**
 * 单向链表
 *
 * 参考：https://www.cnblogs.com/ysocean/p/7928988.html#_label0
 */
public class SingleLinkedList {

    int size;
    Node head;

    class Node {
        Object data;
        Node next;//指向下一节点

        public Node(Object data) {
            this.data = data;
        }
    }

    //在链表头添加元素
    void addHead(Object data) {
        Node newNode = new Node(data);
        if (size == 0) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    //在链表头删除元素
    void deleteHead() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        size--;
    }

    //查找指定元素，找到了返回节点Node，找不到返回null
    Node find(Object data) {
        if (isEmpty()) {
            return null;
        }
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (data.equals(current.data)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }

    //查找指定元素index，找到了返回节点index，找不到返回-1
    int findIndex(Object data) {
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (data.equals(current.data)) {
                return size - tempSize;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return -1;
    }

    //删除指定的元素，删除成功返回true
    boolean delete(Object data) {
        if (isEmpty()) {
            return false;
        }
        Node current = head;
        Node previous = head;
        while (current.data != data) {
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        size--;
        return true;
    }

    //判断链表是否为空
    boolean isEmpty() {
        return size == 0;
    }

    //打印节点信息
    void display() {
        System.out.print("[");
        int tempSize = size;
        if (tempSize > 0) {
            Node node = head;
            while (tempSize > 0) {
                if (tempSize == 1) {
                    System.out.print(node.data);
                } else {
                    System.out.print(node.data + "->");
                }
                node = node.next;
                tempSize--;
            }
        }
        System.out.print("]");
    }

    @Test
    public void testLinked() {
        SingleLinkedList list = new SingleLinkedList();
        list.addHead("A");
        list.addHead("B");
        list.addHead("C");
        list.addHead("D");
        list.display();
        System.out.println();

        System.out.println(list.findIndex("D") + "," + list.findIndex("A"));

        list.deleteHead();
        list.display();
        System.out.println();

        list.delete("C");
        list.display();
        System.out.println();

    }
}
