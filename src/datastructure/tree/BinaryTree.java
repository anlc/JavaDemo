package datastructure.tree;

import org.junit.Test;

/**
 * 二叉树
 * <p>
 * 参考：https://blog.csdn.net/fengrunche/article/details/52305748
 */
public class BinaryTree {

    Node root;

    static class Node {
        int data;
        Node left;//左子节点的引用
        Node right;//右子节点的引用

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "data:" + data;
        }

        public String string() {
            return "current data:" + data + " left:" + (left != null ? left.data : "null") + " right:" + (right != null ? right.data : "null");
        }
    }

    boolean insert(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        }

        Node current = root;
        Node parentNode;
        while (current != null) {
            parentNode = current;
            if (data < current.data) {//当前值比插入值大，搜索左子节点
                current = current.left;
                if (current == null) {
                    parentNode.left = newNode;
                    return true;
                }
            } else if (data > current.data) {//当前值比插入值大，搜索左子节点
                current = current.right;
                if (current == null) {
                    parentNode.right = newNode;
                    return true;
                }
            } else {//数中已经有要插入的值
                return false;
            }
        }

        return false;
    }

    Node find(int data) {
        Node current = root;
        while (current != null) {
            if (data < current.data) {//查找值比当前值小，搜索左子树
                current = current.left;
            } else if (data > current.data) {//查找值比当前值大，搜索右子树
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    //查找最大值节点
    Node findMax() {
        Node current = root;
        while (true) {
            if (current.right == null)
                return current;
            current = current.right;
        }
    }

    //查找最小值节点
    Node findMin() {
        Node current = root;
        while (true) {
            if (current.left == null)
                return current;
            current = current.left;
        }
    }

    //删除节点
    boolean delete(int data) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current != null) {
            if (current.data == data) {
                break;
            } else if (data < current.data) {
                parent = current;
                current = current.left;
                isLeftChild = true;
            } else {
                parent = current;
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.left == null && current.right == null) {//如果当前节点没有子节点
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                current.left = null;
            } else {
                current.right = null;
            }
        } else if (current.left == null && current.right != null) { //当前节点仅有一个右子节点
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right == null) { //当前节点仅有一个左子节点
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {//当前节点存在两个子节点
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
        }
        return true;
    }

    //得到后继节点，即删除节点的左后代
    public Node getSuccessor(Node delNode) {
        Node successor = delNode;
        Node successorParent = null;
        Node current = delNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        //如果后继节点不是删除节点的右子节点时
        if (successor != delNode.right) {
            //要将后继节点的右子节点指向后继结点父节点的左子节点
            successorParent.left = successor.right;
            //要将后继节点的右子节点指向后继结点父节点的左子节点
            successor.right = delNode.right;
        }
        //任何情况下，都需要将删除节点的左子节点指向后继节点的左子节点
        successor.left = delNode.left;
        return successor;
    }

    //中序遍历
    void infixOrder(Node current) {
        if (current != null) {
            infixOrder(current.left);
            System.out.print(current + " ");
            infixOrder(current.right);
        }
    }

    //中序遍历
    void preOrder(Node current) {
        if (current != null) {
            System.out.print(current + " ");
            infixOrder(current.left);
            infixOrder(current.right);
        }
    }

    //后序遍历
    void postOrder(Node current) {
        if (current != null) {
            infixOrder(current.left);
            infixOrder(current.right);
            System.out.print(current + " ");
        }
    }

    @Test
    public void testTree() {
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(8);
        tree.insert(4);
        tree.insert(2);
        tree.insert(1);
        tree.insert(6);
        tree.insert(7);
        tree.insert(9);
//        System.out.println(tree.find(3).string());
//        System.out.println();
//        tree.infixOrder(tree.root);
//        System.out.println();
//        tree.preOrder(tree.root);
//        System.out.println();
//        tree.postOrder(tree.root);
//        System.out.println(tree.findMax());
//        System.out.println(tree.findMin());
        tree.delete(5);
        System.out.println();
    }
}
