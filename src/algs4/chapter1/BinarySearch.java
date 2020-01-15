package algs4.chapter1;

/**
 * 二分查找
 */
public class BinarySearch {

    public static int rank(int key, int[] a) {
        // 必须为有序数组，key一定在 a[lo...hi] 直接，否则不再数组中

        int lo = 0;
        int hi = a.length - 1;

        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 4, 5, 6, 7, 9, 10};

        System.out.println(rank(3, array));
    }
}
