package datastructure.hash;

/**
 * hash
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032656.html
 */
public abstract class HashBase {
    static class DateItem {
        int date;

        public DateItem(int date) {
            this.date = date;
        }
    }

    HashTable.DateItem[] hashArray;     //DataItem类，表示每个数据项信息
    int arraySize;                      //数组的初始大小
    int itemNum;                        //数组实际存储了多少项数据
    HashTable.DateItem nonItem;         //用于删除数据项

    HashBase(int arraySize) {
        this.arraySize = arraySize;
        hashArray = new HashTable.DateItem[arraySize];
        nonItem = new HashTable.DateItem(-1); //删除的数据项下标为-1
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


    /**
     * 数组有固定的大小，而且不能扩展，所以扩展哈希表只能另外创建一个更大的数组，然后把旧数组中的数据插到新的数组中。
     * 但是哈希表是根据数组大小计算给定数据的位置的，所以这些数据项不能再放在新数组中和老数组相同的位置上。
     * 因此不能直接拷贝，需要按顺序遍历老数组，并使用insert方法向新数组中插入每个数据项。
     * 这个过程叫做重新哈希化。这是一个耗时的过程，但如果数组要进行扩展，这个过程是必须的。
     */
    void extendHashTable() {
        int num = arraySize;
        itemNum = 0;//重新计数，因为下面要把原来的数据转移到新的扩张的数组中
        arraySize *= 2;//数组大小翻倍
        DateItem[] oldHashTable = hashArray;
        hashArray = new DateItem[arraySize];
        for (int i = 0; i < num; i++) {
            insert(oldHashTable[i]);
        }
    }

    boolean checkIsError(int key) {
        if (isEmpty()) {
            System.out.println("Hash Table is Empty!");
            return true;
        }
        if (key >= itemNum) {
            return true;
        }
        return false;
    }


    abstract void insert(DateItem item);

    abstract DateItem find(int key);

    abstract DateItem delete(int key);

}
