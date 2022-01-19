package concurrenttest.executor.pazzle;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {

    private final AtomicInteger taskCount = new AtomicInteger(0);

    public PuzzleSolver(Puzzle puzzle, ExecutorService exec, ConcurrentHashMap seen) {
        super(puzzle, exec, seen);
    }

    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return super.newTask(p, m, n);
    }

    class CountingSolverTask extends SolverTask {

        public CountingSolverTask(P p, M m, Node<P, M> n) {
            super(p, m, n);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                if (taskCount.decrementAndGet() != 0) {
                    solution.setValue(null);
                }
            }
        }
    }
}
