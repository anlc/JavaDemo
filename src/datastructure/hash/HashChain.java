package datastructure.hash;

/**
 * hash - 链地址法
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032656.html
 */
public class HashChain {

    SortLink[] hashArray;//数组中存放链表
    int arraySize;

    public HashChain(int arraySize) {
        this.arraySize = arraySize;
        hashArray = new SortLink[arraySize];
        for (int i = 0; i < arraySize; i++) {//new 出每个空链表初始化数组
            hashArray[i] = new SortLink();
        }
    }

    void displayTable() {
        for (int i = 0; i < arraySize; i++) {
            System.out.println(i + " : ");
            hashArray[i].displayLink();
        }
    }

    int hashFunction(int key) {
        return key % arraySize;
    }

    void insert(SortLink.LinkNode node) {
        int hashValue = hashFunction(node.data);
        hashArray[hashValue].insert(node);//直接往链表中添加即可
    }

    SortLink.LinkNode find(int data) {
        int hashValue = hashFunction(data);
        SortLink.LinkNode temp = hashArray[hashValue].find(data);
        return temp;
    }

    SortLink.LinkNode delete(int data) {
        int hashValue = hashFunction(data);
        SortLink.LinkNode temp = find(data);
        hashArray[hashValue].delete(data);
        return temp;
    }


    public static void main(String[] args) {
        HashChain hashChain = new HashChain(10);
        for (int i = 0; i < 30; i++) {
            hashChain.insert(new SortLink.LinkNode(i));
        }
        System.out.println("find : " + hashChain.find(23).data);
        System.out.println("delete : " + hashChain.delete(20).data);
        System.out.println("delete : " + hashChain.delete(25).data);
        hashChain.displayTable();
    }
}
