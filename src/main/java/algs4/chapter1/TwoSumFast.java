package algs4.chapter1;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class TwoSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            // 两个数相加等于零，二分查找数组中位于 a[i] 的值的负数，查到count+1
            if (BinarySearch.rank(-a[i], a) > i) {
                cnt++;
            }
        }
        return cnt;
    }

    public static int count2(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((a[i] + a[j]) == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = new In().readAllInts();
        long start = System.currentTimeMillis();

        System.out.println(count(a));

        System.out.println(System.currentTimeMillis() - start);
    }
}
