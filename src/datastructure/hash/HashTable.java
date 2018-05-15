package datastructure.hash;

/**
 * hash - 线性探测
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032656.html
 */
public class HashTable {

    static class DateItem {
        int date;

        public DateItem(int date) {
            this.date = date;
        }
    }

    private DateItem[] hashArray;   //DataItem类，表示每个数据项信息
    private int arraySize;          //数组的初始大小
    private int itemNum;            //数组实际存储了多少项数据
    private DateItem nonItem;       //用于删除数据项

    HashTable(int arraySize) {
        this.arraySize = arraySize;
        hashArray = new DateItem[arraySize];
        nonItem = new DateItem(-1); //删除的数据项下标为-1
    }

    //判断数组是否存储满了
    boolean isFull() {
        return itemNum == arraySize;
    }

    //判断数组是否为空
    boolean isEmpty() {
        return itemNum == 0;
    }

    //打印数组内容
    void display() {
        System.out.println("Table:");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                System.out.println(hashArray[i].date + " ");
            } else {
                System.out.println("** ");
            }
        }
    }

    //通过哈希函数转换得到数组下标
    int hashFunction(int key) {
        return key % arraySize;
    }

    //插入数据项
    void insert(DateItem item) {
        if (isFull()) {
            System.out.println("哈希表已满，重新哈希化...");
            extendHandler();
        }

        int key = item.date;
        int hashValue = hashFunction(key);
        while (hashArray[hashValue] != null && hashArray[hashValue].date != -1) {
            ++hashValue;
            hashValue %= arraySize;
        }
        hashArray[hashValue] = item;
        itemNum++;
    }

    /**
     * 数组有固定的大小，而且不能扩展，所以扩展哈希表只能另外创建一个更大的数组，然后把旧数组中的数据插到新的数组中。
     * 但是哈希表是根据数组大小计算给定数据的位置的，所以这些数据项不能再放在新数组中和老数组相同的位置上。
     * 因此不能直接拷贝，需要按顺序遍历老数组，并使用insert方法向新数组中插入每个数据项。
     * 这个过程叫做重新哈希化。这是一个耗时的过程，但如果数组要进行扩展，这个过程是必须的。
     */
    void extendHandler() {
        int num = arraySize;
        itemNum = 0;//重新计数，因为下面要把原来的数据转移到新的扩张的数组中
        arraySize *= 2;
        DateItem[] oldHashTable = hashArray;
        hashArray = new DateItem[arraySize];
        for (int i = 0; i < num; i++) {
            insert(oldHashTable[i]);
        }
    }

    //删除数据项
    DateItem delete(int key) {
        if (isEmpty()) {
            System.out.println("Hash Table is Empty!");
            return null;
        }

        int hashValue = hashFunction(key);
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].date == key) {
                DateItem temp = hashArray[hashValue];
                hashArray[hashValue] = nonItem;
                itemNum--;
                return temp;
            }
            ++hashValue;
            hashValue %= arraySize;
        }
        return null;
    }

    //查找数据项
    DateItem find(int key) {
        if (key >= itemNum) {
            return null;
        }
        int hashValue = hashFunction(key);
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].date == key) {
                return hashArray[hashValue];
            }
            ++hashValue;
            hashValue %= arraySize;
        }
        return null;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        for (int i = 0; i < 30; i++) {
            hashTable.insert(new DateItem(i));
        }
        System.out.println(hashTable.find(23).date);
        hashTable.display();
    }
}
