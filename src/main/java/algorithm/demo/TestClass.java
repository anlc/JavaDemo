package algorithm.demo;

public class TestClass {

    public static int qcd(int p, int q) {
        if (p == 0) return q;
        if (q == 0) return p;

        int temp = p % q;
        return qcd(q, temp);
    }

    public static void main(String[] args) {
        System.out.println(qcd(5, 3));
    }
}
