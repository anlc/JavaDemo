package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 两数相加
 */
public class PlusTwoNumber {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(2, 4, 3);
        List<Integer> list2 = Arrays.asList(5, 6, 4);
        List<Integer> resultList = new ArrayList<>();

        boolean isNeedCarry = false;
        for (int i = 0; i < list1.size(); i++) {
            int result = list1.get(i) + list2.get(i);
            if (isNeedCarry) {
                result++;
            }
            if (isNeedCarry = result >= 10) {
                result = result - 10;
            }
            resultList.add(result);
        }
        System.out.println(resultList);
    }
}
