package datastructure.tree;

import org.junit.Test;

/**
 * 2-3-4 树
 * <p>
 * 参考：
 * http://www.cnblogs.com/ysocean/p/8032648.html
 */
public class Tree234 {

    @Test
    public void testTree() {
        Tree234 tree = new Tree234();
        for (int i = 0; i < 20; i++)
            tree.insert(i);
        tree.displayTree();
    }

    Node root = new Node();

    //查找关键字值
    int find(long key) {
        Node curNode = root;
        int childNum;
        while (true) {
            if ((childNum = curNode.findItem(key)) != -1) {
                return childNum;
            } else if (curNode.isLeaf()) {//节点是叶节点
                return -1;
            } else {
                curNode = getNextChild(curNode, key);
            }
        }

    }

    Node getNextChild(Node node, long value) {
        int i;
        int numItems = node.getNumItems();
        for (i = 0; i < numItems; i++) {
            if (value < node.getItem(i).dData) {
                return node.getChild(i);
            }
        }
        return node.getChild(i);
    }

    //插入数据项
    void insert(int value) {
        Node curNode = root;
        DataItem item = new DataItem(value);
        while (true) {
            if (curNode.isFull()) {//如果节点满数据项了，则分裂节点
                split(curNode);
                curNode = curNode.getParent();
                curNode = getNextChild(curNode, value);
            } else if (curNode.isLeaf()) {//当前节点是叶节点
                break;
            } else {
                curNode = getNextChild(curNode, value);
            }
        }
        curNode.insertItem(item);
    }

    void split(Node node) {
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;
        itemC = node.removeItem();
        itemB = node.removeItem();
        child2 = node.disconnectChild(2);
        child3 = node.disconnectChild(3);

        Node newRight = new Node();
        if (node == root) {//如果当前节点是根节点，执行根分裂
            root = new Node();
            parent = root;
            root.connectChild(0, node);
        } else {
            parent = node.getParent();
        }

        //处理父节点
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();
        for (int i = n - 1; i > itemIndex; i--) {
            Node temp = parent.disconnectChild(i);
            parent.connectChild(i + 1, temp);
        }
        parent.connectChild(itemIndex + 1, newRight);

        //处理新建的右节点
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }

    //打印树节点
    void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node node, int level, int childNum) {
        System.out.println("level:" + level + "  child:" + childNum);
        node.displayNode();
        int numItems = node.getNumItems();
        for (int i = 0; i < numItems + 1; i++) {
            Node nextNode = node.getChild(i);
            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, i);
            } else {
                return;
            }
        }
    }

    //数据项
    class DataItem {
        public long dData;

        public DataItem(long dData) {
            this.dData = dData;
        }

        public void displayItem() {
            System.out.println("/" + dData);
        }
    }

    class Node {
        private final int ORDER = 4;
        int numItems;//表示该节点有多少个数据项
        Node parent;//父节点
        Node childArr[] = new Node[ORDER];//存储子节点的数组，最多有4个子节点
        DataItem itemArr[] = new DataItem[ORDER - 1];//存放数据项的数组，一个节点最多有三个数据项

        //连接子节点
        void connectChild(int childNum, Node child) {
            childArr[childNum] = child;
            if (child != null) {
                child.parent = this;
            }
        }

        //断开与子节点的连接，并返回该子节点
        Node disconnectChild(int childNum) {
            Node tempNode = childArr[childNum];
            childArr[childNum] = null;
            return tempNode;
        }

        //得到节点的某个子节点
        Node getChild(int childNum) {
            return childArr[childNum];
        }

        //得到父节点
        Node getParent() {
            return parent;
        }

        //判断是否是叶节点
        boolean isLeaf() {
            return childArr[0] == null;
        }

        //得到节点数据项的个数
        int getNumItems() {
            return numItems;
        }

        //得到节点的某个数据项
        DataItem getItem(int index) {
            return itemArr[index];
        }

        //判断节点的数据项是否满了（最多3个）
        boolean isFull() {
            return numItems == ORDER - 1;
        }

        //找到数据项在节点中的位置
        int findItem(long key) {
            for (int i = 0; i < ORDER - 1; i++) {
                if (itemArr[i] == null)
                    break;
                else if (itemArr[i].dData == key)
                    return i;
            }
            return -1;
        }

        //将数据项插入到节点
        int insertItem(DataItem item) {
            numItems++;
            long newKey = item.dData;
            for (int i = ORDER - 2; i >= 0; i--) {
                if (itemArr[i] == null) {//如果为空，继续向前循环
                    continue;
                } else {
                    long itsKey = itemArr[i].dData;
                    if (newKey < itsKey) {//如果比新插入的数据项大
                        itemArr[i + 1] = itemArr[i];//将大数据项向后移动一位
                    } else {
                        itemArr[i + 1] = item;//如果比新插入的数据项小，则直接插入
                        return i + 1;
                    }
                }
            }

            //如果都为空，或者都比待插入的数据项大，则将待插入的数据项放在节点第一个位置
            itemArr[0] = item;
            return 0;
        }

        //移除节点的数据项
        DataItem removeItem() {
            DataItem temp = itemArr[numItems - 1];
            itemArr[numItems - 1] = null;
            numItems--;
            return temp;
        }

        //打印节点的所有数据项
        void displayNode() {
            for (int i = 0; i < numItems; i++) {
                itemArr[i].displayItem();
            }
            System.out.println("/");
        }
    }
}
