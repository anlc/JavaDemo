package datastructure.tree;

import org.junit.Test;

/**
 * 红黑树
 * <p>
 * 参考：
 * http://www.cnblogs.com/ysocean/p/8004211.html
 * https://www.cnblogs.com/zedosu/p/6635034.html
 * https://www.cnblogs.com/springsource/p/6419462.html
 */
public class RBTree<T extends Comparable<T>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private Node<T> root;

    class Node<T> {
        boolean color;
        T data;         //键值
        Node left;      //左节点
        Node right;     //右节点
        Node parent;    //父节点

        public Node(boolean color, T data, Node left, Node right, Node parent) {
            this.color = color;
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(T data) {
            this.color = RED;
            this.data = data;
        }

        public String toString() {
            return data + (this.color == RED ? "(R)" : "B");
        }

        public boolean isRed() {
            return color == RED;
        }

        public void setBlack() {
            color = BLACK;
        }

        public void setRed() {
            color = RED;
        }
    }

    void insert(T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> current = null;//表示最后node的父节点
        Node<T> x = root;//用来向下搜索
        while (x != null) {
            current = x;
            if (newNode.data.compareTo(x.data) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        newNode.parent = current;//找到了插入的位置，将当前current作为node的父节点
        if (current == null) {
            root = newNode;
        } else {
            if (newNode.data.compareTo(current.data) < 0) {
                current.left = newNode;
            } else {
                current.right = newNode;
            }
        }

        //利用旋转操作将其修正为一颗红黑树
        insertFixUp(newNode);
    }

    /*
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     */
    void insertFixUp(Node node) {

        Node<T> parent, gParent;//定义父节点和祖父节点

        //需要修正的条件：父节点存在，且父节点的颜色是红色
        while ((parent = parentOf(node)) != null && parent.isRed()) {
            gParent = parentOf(parent);//获得祖父节点

            //若父节点是祖父节点的左子节点，下面的else相反
            if (parent == gParent.left) {
                Node<T> uncle = gParent.right;//获得叔叔节点

                //case1:叔叔节点也是红色
                if (uncle != null && uncle.isRed()) {
                    parent.setBlack();//把父节点和叔叔节点涂黑
                    uncle.setBlack();
                    gParent.setRed();//把祖父节点涂红
                    node = gParent;//把位置放到祖父节点处
                    continue;//继续while循环，重新判断
                }

                //case2:叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.right) {
                    leftRotate(parent);//从父节点处旋转
                    Node<T> temp = parent;//然后将父节点和自己调换一下，为下面右旋做准备
                    parent = node;
                    node = temp;
                }

                //case3:叔叔节点是黑色，且当前节点是左子节点
                parent.setBlack();
                gParent.setRed();
                rightRotate(gParent);
            } else {//若父节点是祖父节点的右子节点，与上面的情况完全相反，本质是一样的
                Node<T> uncle = gParent.left;//获得叔叔节点

                //case1:叔叔节点也是红色的
                if (uncle != null && uncle.isRed()) {
                    parent.setBlack();
                    uncle.setBlack();
                    gParent.setRed();
                    node = gParent;
                    continue;
                }

                //case2:叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    Node<T> temp = parent;
                    parent = node;
                    node = temp;
                }

                parent.setBlack();
                gParent.setRed();
                leftRotate(gParent);
            }
        }
        root.setBlack();//将根节点设置为黑色
    }

    private Node parentOf(Node node) {
        return node.parent;
    }

    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(Node x) {
        Node<T> y = x.right;//将y设为x的右子节点，使原数据与示意图成等式

        x.right = y.left;   //1. 将y的左子节点赋给x的右子节点
        if (y.left != null) {
            y.left.parent = x; //y左子节点非空时, 将x赋给y左子节点的父节点
        }
        y.parent = x.parent; //将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        if (x.parent == null) {
            root = y;//如果x的父节点为空(即x为根节点)，则将y设为根节点
        } else {
            //用y替换原来x在父节点的位置
            if (x == x.parent.left) {//如果x是父节点的左子节点
                x.parent.left = y; //将x的父节点的左子节点设为y
            } else {
                x.parent.right = y;//否则，将x的父节点的右子节点设为y
            }
        }

        y.left = x;
        x.parent = y;
    }

    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(Node y) {
        Node<T> x = y.left;//将x设为y的左子节点，使原数据与示意图成等式

        //1. 将x的右子节点赋给y的左子节点
        y.left = x.right;
        if (x.left != null) {
            x.left.parent = y; //x右子节点非空时, 将y赋给x右子节点的父节点
        }

        //2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else {//如果x的父节点为空(即x为根节点)，则将y设为根节点
            //用x替换原来y在父节点的位置
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }

        //3. 将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }

    @Test
    public void testTree() {
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 1; i < 10; i++) {
            tree.insert(i);
        }
        System.out.println();
    }

}
