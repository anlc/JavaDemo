package algs4.chapter1;

public class Stopwatch {

    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long time = System.currentTimeMillis() - start;
        return time / 1000.0;
    }
}
