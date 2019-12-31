package pattern.design_pattern.creational_patterns.singleton;

import org.junit.Test;

/**
 * 双重检查锁单例模式
 */
public class DoubleCheckSingletonPattern {

    static class Singleton {
        private volatile static Singleton INSTANCE;

        private static Singleton getInstance() {
            if (INSTANCE == null) {
                synchronized (Singleton.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new Singleton();
                    }
                }
            }
            return INSTANCE;
        }

        @Override
        public String toString() {
            return "double check singleton pattern";
        }
    }

    @Test
    public void testPattern() {
        System.out.println(Singleton.getInstance().toString());
    }
}
