package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 参考：http://www.cnblogs.com/ysocean/p/7896269.html
 */
public class BubbleSort {

    void sort(int[] array) {
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {//跳过对最后一次有序排序
                break;
            }
            flag = false;
        }
    }

    @Test
    public void testSort() {
        int[] array = {5, 8, 6, 2, 3, 4, 9, 7, 1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
