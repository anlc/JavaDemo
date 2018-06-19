package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 希尔排序
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032632.html
 */
public class ShellSort {

    public void sort(int step, int[] array){
        for (int i = step; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > step - 1 && temp <= array[j - step]) {
                array[j] = array[j - step];
                j -= step;
            }
            array[j] = temp;
        }
        System.out.println("间隔为" + step + "的排序结果为" + Arrays.toString(array));
    }

    //希尔排序 knuth 间隔序列 3h+1
    public void shellKnuthSort(int[] array) {
        System.out.println("原数组为：" + Arrays.toString(array));
        int step = 1;
        while (step <= array.length / 3) {
            step = step * 3 + 1;//1,4,13,40......
        }
        while (step > 0) {
            //分别对每个增量间隔进行排序
            sort(step, array);
            step = (step - 1) / 3;
        }
        System.out.println("最终排序：" + Arrays.toString(array));
    }

    //希尔排序 间隔序列2h
    public void shellSort(int[] array) {
        System.out.println("原数组为" + Arrays.toString(array));
        int len = array.length;
        for (int step = len / 2; step > 0; step /= 2) {
            //分别对每个增量间隔进行排序
            sort(step, array);
        }
    }

    @Test
    public void testSort() {
        int[] array = {4, 2, 8, 9, 5, 7, 6, 1, 3, 10};
        shellKnuthSort(array);
//        shellSort(array);
    }
}
