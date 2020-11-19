package algs.c2;

import java.util.Arrays;

public class MergeSort {

    int[] array = {1, 4, 5, 2, 3, 6, 7, 8, 9};
    int[] temp = new int[array.length];

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(0, mergeSort.array.length - 1);
        System.out.println(Arrays.toString(mergeSort.array));
    }


    public void mergeSort(int lo, int hi) {

//        System.out.println("lo:" + lo + ", hi:" + hi);
        if (lo >= hi) {
            return;
        }

//        int mi = (lo + hi) >> 1;
        int mi = lo + (hi - lo) / 2;

        mergeSort(lo, mi);
        mergeSort(mi + 1, hi);

        sort(lo, mi, hi);
    }

    public void sort(int lo, int mi, int hi) {
        int i = lo, j = mi + 1;
        for (int k = 0; k <= hi; k++) {
            temp[k] = array[k];
        }

        for (int k = 0; k <= hi; k++) {
            if (i > mi) array[k] = temp[j++];
            else if (j > hi) array[k] = temp[i++];
//            else if()
        }
    }
}
