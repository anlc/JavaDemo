package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序
 *
 * 参考：http://www.cnblogs.com/ysocean/p/7896269.html
 */
public class ChoiceSort {

    void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j; //记录数组中最小数值的角标
                }
            }
            if (min != i) {
//                int temp = array[i];
//                array[i] = array[min];
//                array[min] = temp;

                /**
                 * 通过异或交换两个值
                 * 异或运算的特点，通过异或运算能够使数据中的某些位翻转，其他位不变。这就意味着任意一个数与任意一个给定的值连续异或两次，值不变
                 * 参考：https://www.cnblogs.com/lehte-ice/p/4232598.html
                 */
                array[i] = array[i] ^ array[min];
                array[min] = array[i] ^ array[min];
                array[i] = array[i] ^ array[min];
            }
        }
    }

    @Test
    public void testSort() {
        int[] array = {5, 8, 6, 2, 3, 4, 9, 7, 1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
