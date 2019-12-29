package algs4.chapter1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class TestVisualAccumulator {

    public static void main(String[] args) {
//        int t = Integer.parseInt(args[0]);
        int t = 1000;
        VisualAccumulator visualAccumulator = new VisualAccumulator(t, 1.9);
        for (int i = 0; i < t; i++) {
            visualAccumulator.addDataValue(i);
        }
        StdOut.println(visualAccumulator);
    }


    static class VisualAccumulator {

        private double total;
        private int N;

        public VisualAccumulator(int trials, double max) {
            StdDraw.setXscale(0, trials);
            StdDraw.setYscale(0, max);
            StdDraw.setPenRadius(.005);
        }

        public void addDataValue(double val) {
            N++;
            total += val;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(N, val);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(N, total / val);
        }

        public double mean() {
            return total / N;
        }

        @Override
        public String toString() {
            return "Mean(" + N + " values" + "): " + String.format("%7.5f", mean());
        }
    }

}
