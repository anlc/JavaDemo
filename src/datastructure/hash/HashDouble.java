package datastructure.hash;

/**
 * hash - 再哈希法
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032656.html
 */
public class HashDouble extends HashBase {

    int constant = 5;

    HashDouble() {
        super(13);//再哈希法要求表的容量是一个质数
    }

    int hashFunction1(int key) {
        return key % arraySize;
    }

    /**
     * 1: 不能输出0（否则，将没有步长，每次探测都是原地踏步，算法将陷入死循环）
     * 2: 专家们已经发现下面形式的哈希函数工作的非常好：stepSize = constant - key % constant; 其中constant是质数，且小于数组容量。
     */
    int hashFunction2(int key) {
        return constant - key % constant;
    }

    @Override
    void insert(DateItem item) {
        if (isFull()) {
            //扩展哈希表
            System.out.println("哈希表已满，重新哈希化...");
            extendHashTable();
        }

        int key = item.date;
        int hashValue = hashFunction1(key);
        int stepSize = hashFunction2(key);//用第二个哈希函数计算探测步数
        while (hashArray[hashValue] != null && hashArray[hashValue].date != -1) {
            hashValue += stepSize;
            hashValue %= stepSize;
        }
        hashArray[hashValue] = item;
        itemNum++;
    }

    @Override
    DateItem find(int key) {
        if (checkIsError(key)) return null;
        int hashValue = hashFunction1(key);
        int stepSize = hashFunction2(key);
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].date == key) {
                return hashArray[hashValue];
            }
            hashValue += stepSize;
            hashValue %= stepSize;
        }
        return null;
    }

    @Override
    DateItem delete(int key) {
        if (checkIsError(key)) return null;
        int hashValue = hashFunction1(key);
        int stepSize = hashFunction2(key);
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].date == key) {
                DateItem temp = hashArray[hashValue];
                hashArray[hashValue] = nonItem;//nonItem表示空Item,其key为-1
                itemNum--;
                return temp;
            }
            hashValue += stepSize;
            hashValue %= stepSize;
        }
        return null;
    }

    public static void main(String[] args) {
        HashDouble hashTable = new HashDouble();
        for (int i = 0; i < 30; i++) {
            hashTable.insert(new DateItem(i));
        }
        System.out.println("find : " + hashTable.find(23).date);
        System.out.println("delete : " + hashTable.delete(20).date);
        System.out.println("delete : " + hashTable.delete(25).date);
        hashTable.display();
    }
}
