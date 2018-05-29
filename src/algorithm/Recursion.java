package algorithm;

/**
 * 递归
 *
 * 参考：https://www.cnblogs.com/ysocean/p/8005694.html
 */
public class Recursion {

    //求一个数的阶乘
    public static int getFactorial(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            System.out.println(n + "!=1");
            return 1;
        } else {
            System.out.println(n);
            int temp = n * getFactorial(n - 1);
            System.out.println(n + "!=" + temp);
            return temp;
        }
    }

    //二分查找
    public static int search(int array[], int key, int low, int height) {
        int mid = (height - low) / 2 + low;
        if (key == array[mid]) {
            return mid;
        } else if (low > height) {
            return -1;
        } else {
            if (key < array[mid]) {
                return search(array, key, low, mid - 1);
            }
            if (key > array[mid]) {
                return search(array, key, mid + 1, height);
            }
        }
        return -1;
    }


    public static void main(String[] args) {
//        System.out.println("getFactorial: " + getFactorial(4));
//        System.out.println("search index: " + search(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 9, 1, 9));
    }
}
