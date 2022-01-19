package algs.c1;

public class HailstoneDemo {

    public static void main(String[] args) {
        System.out.println(" length: " + hailstone2(10009));
    }

    static int hailstone2(int n) {
        int length = 1;
        while (n > 1) {
            n = n % 2 == 0 ? n / 2 : n * 3 + 1;
            length++;
            System.out.printf(n + "\t");
        }
        System.out.println();
        return length;
    }

    static int length = 1;

    static int hailstone(int n) {
        System.out.printf(n + "\t");
        if (n == 1) {
            return 1;
        }
        length++;

        int i = n % 2 == 0 ? n / 2 : n * 3 + 1;

        if (i > 1) {
            hailstone(i);
        }

        return length;
    }
}
