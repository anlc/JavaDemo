package algs4.chapter1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DoublingTest {

    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch stopwatch = new Stopwatch();
        ThreeSum.count(a);
        return stopwatch.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = 1;
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%6d  %7.1f ", N, time);
            StdOut.printf("%5.1f \n", time / prev);
            prev = time;
        }
    }
}
