package pattern.design_pattern.creational_patterns.factory;

import org.junit.Test;

/**
 * 反射的方式创建对象
 */
public class ReflectFactoryPattern {

    interface Shape {
    }

    static class Circle implements Shape {
        @Override
        public String toString() {
            return "create circle";
        }
    }

    static class Square implements Shape {
        @Override
        public String toString() {
            return "create square";
        }
    }

    static class Factory {
        public static <T extends Shape> T crate(Class<T> clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Test
    public void testPattern() {
        System.out.println(Factory.crate(Circle.class));
        System.out.println(Factory.crate(Square.class));
    }
}
