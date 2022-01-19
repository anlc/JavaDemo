package other;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class TestMethodHandle {

    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather{
        @Override
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father{
        @Override
        void thinking() {
            try {
                MethodType methodType = MethodType.methodType(void.class);
                MethodHandle thinking = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", methodType, getClass());
                thinking.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TestMethodHandle().new Son().thinking();
    }
}
