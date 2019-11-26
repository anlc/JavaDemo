package other.proxy;

import other.SetFlag;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClassProxy {

    private A a;

    public ClassProxy(A aClass) {
        this.a =aClass;
    }

    public Object progress(Class<?>... classes) {
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), classes, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                method.invoke(a, args);
                System.out.println(proxy.getClass().getSimpleName());
                System.out.println(method);
                System.out.println(args);
                return null;
            }
        });
    }

    interface A {
        void main();
    }

    static class B implements A {

        @Override
        public void main() {
            System.out.println("--------");

        }

        void test() {
        }
    }

    public static void main(String[] args) {
        A aClass = new B();
        A a = (A) new ClassProxy(aClass).progress(A.class);
        a.main();
    }
}
