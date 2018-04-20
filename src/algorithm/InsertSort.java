package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 *
 * 参考：http://www.cnblogs.com/ysocean/p/7896269.html
 */
public class InsertSort {

    void sort(int[] array) {

        int j;//记录要插入的角标

        for (int i = 1; i < array.length; i++) {
            int temp = array[i]; //要插入的值
            j = i;

            while (j > 0 && temp < array[j - 1]) {//从已经排序的序列最右边的开始比较，找到比其小的数
                array[j] = array[j - 1];//向后挪动
                j--;
            }
            array[j] = temp;
        }
    }

    @Test
    public void testSort() {
        int[] array = {5, 8, 6, 2, 3, 4, 9, 7, 1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
