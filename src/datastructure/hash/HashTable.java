package datastructure.hash;

/**
 * hash - 线性探测
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032656.html
 */
public class HashTable extends HashBase {

    HashTable(int arraySize) {
        super(arraySize);
    }

    //通过哈希函数转换得到数组下标
    int hashFunction(int key) {
        return key % arraySize;
    }

    //插入数据项
    void insert(DateItem item) {
        if (isFull()) {
            System.out.println("哈希表已满，重新哈希化...");
            extendHashTable();
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

    //删除数据项
    DateItem delete(int key) {
        if(checkIsError(key)) return null;
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
        if(checkIsError(key)) return null;
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
        System.out.println("find : " + hashTable.find(23).date);
        System.out.println("delete : " + hashTable.delete(20).date);
        System.out.println("delete : " + hashTable.delete(25).date);
        hashTable.display();
    }
}
