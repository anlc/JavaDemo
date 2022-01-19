package algs.c1;

import org.junit.Test;

import java.util.Arrays;

public class ReverseDemo {

    @Test
    public void testReverse() {
        int[] array = {1, 2, 3, 5};
        reverse2(array);
        System.out.println(Arrays.toString(array));
    }

    void reverse(int[] array, int low, int hi) {
        if (low < hi) {
            int temp = array[low];
            array[low] = array[hi];
            array[hi] = temp;
            reverse(array, low + 1, hi - 1);
        }
    }

    // 颠倒数组
    void reverse2(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    @Test
    public void testSumAdd() {
        int[] array = {1, 2, 3, 5};
//        System.out.println(add(array, 0, array.length - 1));
        System.out.println(add3(array, array.length));
    }

    // 分而治之
    int add(int[] array, int lo, int hi) {
        if (lo == hi) return array[lo];
        int mi = (lo + hi) / 2;
        return add(array, lo, mi) + add(array, mi + 1, hi);
    }

    // 减而治之
    int add2(int[] array, int n) {
        return n < 1 ? 0 : add2(array, n - 1) + array[n - 1];
    }

    int add3(int[] array, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum;
    }

    @Test
    public void testFindMax2() {
        int[] array = {1, 2, 3, 5, 9, 6, 7, 8, 10};
//        findMax2(array);
        findMax3(array, 0, array.length, array[1], array[2]);
    }

    void findMax2(int[] array) {
        int max1 = array[0];
        int max2 = array[1];
        for (int i = 1; i < array.length; i++) {
//            if (max1 < array[i]) {
//                max2 = max1;
//                max1 = array[i];
//            } else if (max2 < array[i]) {
//                max2 = array[i];
//            }

            if (max2 < array[i]) {
                max2 = array[i];
                if (max1 < max2) {
                    max2 = max1;
                    max1 = array[i];
                }
            }
        }
        System.out.println("max1: " + max1 + "  max2: " + max2);
    }

    void findMax22(int[] array, int lo, int hi) {
        int max1 = array[lo];
        int max2 = array[lo + 1];
        if (max1 < max2) {
            int temp = max1;
            max1 = max2;
            max2 = temp;
        }

        for (int i = lo + 2; i < hi; i++) {
            if (max2 < array[i]) {
                if (max1 < (max2 = array[i])) {
                    int temp = max1;
                    max1 = max2;
                    max2 = temp;
                }
            }
        }

        System.out.println("max1: " + max1 + "  max2: " + max2);
    }

    void findMax3(int[] array, int lo, int hi, int x1, int x2) {

        System.out.println(" -- :" + lo + "," + hi + ","+ x1 + "," + x2);
        if (lo + 2 == hi) {
            System.out.println(array[x1] + ", " + array[x2]);
            return;
        }
        if (lo + 3 == hi) {
            System.out.println(array[x1] + ", " + array[x2]);
            return;
        }

        int mi = (lo + hi) / 2;

        Integer x1l = 0, x2l = 0;
        findMax3(array, lo, mi, x1l, x2l);

        Integer x1r = 0, x2r = 0;
        findMax3(array, mi, hi, x1r, x2r);

        if (array[x1l] > array[x1r]) {
            x1 = x1l;
            x2 = array[x2l] > array[x1r] ? x2l : x1r;
        } else {
            x1 = x1r;
            x2 = array[x1l] > array[x2r] ? x1l : x2r;
        }
    }
}
