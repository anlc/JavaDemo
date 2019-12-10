package other;

import java.util.Arrays;
import java.util.List;

public class TestInteger {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3l;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//
//        int sum = 0;
//        for (int i : list) {
//            sum += i;
//        }
//        System.out.println(sum);

    }
}
