package leetcode;

import java.util.Arrays;

public class MinNum {

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 3};
//        int[] nums2 = new int[]{2};

        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.printf("result: " + findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] tempArray = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, tempArray, 0, nums1.length);
        System.arraycopy(nums2, 0, tempArray, nums1.length, nums2.length);
        Arrays.sort(tempArray);
        System.out.println(Arrays.toString(tempArray));
        int i = tempArray.length % 2;
        if (i == 0) {
            int minArrayMaxNum = tempArray[tempArray.length / 2 - 1];
            int maxArrayMinNum = tempArray[tempArray.length / 2];
            return (minArrayMaxNum + maxArrayMinNum) / (double) 2;
        }
        return tempArray[tempArray.length / 2];
    }
}
