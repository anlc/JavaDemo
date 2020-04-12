package algs.c1;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {

    @Test
    public void testBubble() {
        int[] array = {4, 2, 8, 9, 5, 7, 6, 1, 3};
        bubbleSort2(array);
    }

    void bubbleSort2(int[] array) {
        int n = array.length - 1;
        for (boolean sorted = false; sorted = !sorted; n--) {
            for (int j = 0; j < n; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                }
                System.out.printf("-----");
            }
            System.out.println();
            System.out.println("sort: " + Arrays.toString(array));
        }
    }

    void bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
                System.out.printf(i + "\t");
            }
            System.out.println();
            System.out.println("i: " + i);
            if (flag) {
//                System.out.println("break: " + i + " :: " + array.length + " :: " + Arrays.toString(array));
                break;
            }
            System.out.println("sort: " + Arrays.toString(array));
        }
        System.out.println("result: " + Arrays.toString(array));
    }

    void bubbleSort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
                System.out.printf(i + "\t");
            }
            System.out.println();
            if (flag) {
                break;
            }
            //第 i轮排序的结果为
            System.out.print("第" + i + "轮排序后的结果为:");
            System.out.println(Arrays.toString(array));
        }
    }
}
