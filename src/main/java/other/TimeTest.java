package other;

import org.junit.Test;

public class TimeTest {

    static class SuperClass {
        static {
            System.out.println("Super Class Init");
        }

        public static final int value = 123;
        public static String value2 = "123";

        public static String testMethod(){
            return "123";
        }
    }

    static class SubClass extends SuperClass {
        static {
            System.out.println("Sub Class Init");
        }
    }

    @Test
    public void testTime() {
        System.out.println(SuperClass.value2);
    }
}
