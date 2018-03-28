package pattern.design_pattern.singleton;

import org.junit.Test;

/**
 * 单例模式
 * 登记式/静态内部类
 *
 * 参考：http://www.runoob.com/design-pattern/singleton-pattern.html
 */
public class StaticSingletonPattern {

    static class Singleton{

        private Singleton(){}

        private static class Holder{
            private static final Singleton INSTANCE = new Singleton();
        }

        public static Singleton getInstance(){
            return Holder.INSTANCE;
        }

        @Override
        public String toString() {
            return "static inner class singleton pattern";
        }
    }

    @Test
    public void testPattern(){
        System.out.println(Singleton.getInstance().toString());
    }
}
