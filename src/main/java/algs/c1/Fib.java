package algs.c1;


import org.junit.Test;

public class Fib {

    @Test
    public void testFib() {
        for (int i = 0; i < 9; i++) {
            System.out.print("fib(" + i + ") = " + fib(i) + ", ");
        }
        System.out.println();
        fib2(64);
    }

    int fib(int n) {

        // fib(43) = 2^30 = (210)3 = (103)3 = 10^9 flo = 1 sec
        // 当n=43时, 大概是一秒

        int result = n < 2 ? n : (fib(n - 1) + fib(n - 2));
        return result;
    }

    void fib2(int n) {
        long f = 0, g = 1;

        while (0 < n--) {
            System.out.println("fib(" + n + ") = " + f + ", ");
            g = g + f;
            f = g - f;
        }
    }
}
