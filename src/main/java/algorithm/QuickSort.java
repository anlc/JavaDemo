package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032632.html
 */
public class QuickSort {

    //数组array中下标为i和j位置的元素进行交换
    void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    void quickSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        } else {
            int partition = partitionIt(array, left, right);
            quickSort(array, left, partition - 1);
            quickSort(array, partition + 1, right);
        }
    }

    int partitionIt(int[] array, int left, int right) {
        int i = left;
        int j = right + 1;
        int pivot = array[left]; //pivot 为选取的基准元素（头元素）

        //优化
        int size = right - left + 1;
        if(size >= 3){
            pivot = medianOf3(array, left, right); //数组范围大于3，基准元素选择中间值。
        }

        while (true) {
            while (i < right && array[++i] < pivot) {

            }
            while (j > 0 && array[--j] > pivot) {

            }
            if (i >= j) { //左右游标相遇时候停止， 所以跳出外部while循环
                break;
            } else { //左右游标未相遇时停止, 交换各自所指元素，循环继续
                swap(array, i, j);
            }
        }
        swap(array, left, j);
        return j;
    }

    //取数组下标第一个数、中间的数、最后一个数的中间值
    int medianOf3(int[] array, int left, int right) {
        int center = (right - left) / 2 + left;
        if (array[left] > array[right]) { //得到 array[left] < array[right]
            swap(array, left, right);
        }
        if (array[center] > array[right]) { //得到 array[center] < array[right]
            swap(array, center, right);
        }
        if (array[center] > array[left]) {//得到 array[center] <  array[left] < array[right]
            swap(array, center, left);
        }
        return array[left]; //array[left]的值已经被换成三数中的中位数， 将其返回
    }

    void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    @Test
    public void testSort() {
        int[] array = {7, 3, 5, 2, 9, 8, 6, 1, 4, 7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
