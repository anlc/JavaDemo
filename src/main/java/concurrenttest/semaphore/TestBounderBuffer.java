package concurrenttest.semaphore;

import junit.framework.TestCase;

public class TestBounderBuffer extends TestCase {

    public void testEmptyWhenConstructed(){
        BounderBuffer<Integer> bb = new BounderBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }
    public void testIfFullAfterPuts() throws InterruptedException {
        BounderBuffer<Integer> bb = new BounderBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }
}
