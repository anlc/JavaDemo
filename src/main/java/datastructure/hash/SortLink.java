package datastructure.hash;

/**
 * 有序列表
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032656.html
 */
public class SortLink {

    LinkNode first;

    boolean isEmpty() {
        return first == null;
    }

    void insert(LinkNode node) {
        LinkNode previous = null;
        LinkNode current = first;
        while (current != null && current.data < node.data) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            first = node;
        } else {
            node.next = current;
            previous.next = node;
        }
    }

    LinkNode find(int value) {
        LinkNode current = first;
        while (current != null && current.data <= value) {
            if (current.data == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    void delete(int value) {
        LinkNode previous = null;
        LinkNode current = first;
        if (isEmpty()) {
            System.out.println("Linked is Empty!!!");
            return;
        }
        while (current != null && current.data != value) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
    }

    void displayLink() {
        System.out.println("Link(First->Last)");
        LinkNode current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    static class LinkNode {
        int data;
        LinkNode next;

        LinkNode(int data) {
            this.data = data;
        }

        void displayLink() {
            System.out.println(data + " ");
        }
    }
}
