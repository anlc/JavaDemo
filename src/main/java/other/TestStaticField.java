package other;

public class TestStaticField {

    interface A{
        int A = 0;
    }

    interface B{
        int A = 1;
    }

    static class Parent implements A{
        public static int A = 3;
    }

    static class Sub extends Parent implements B{
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
