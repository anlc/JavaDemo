package pattern.head_first.creational;

public class SingletonPattern {

    public static final class Singleton {

        private volatile static Singleton INSTANCE;

        /**
         * 将构造方法私有化
         */
        private Singleton() {}

        /**
         * 提供一个静态方法获取实例对象
         */
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

        public void display(){
            System.out.println("display");
        }
    }

    public static void main(String[] args) {
        Singleton.getInstance().display();
    }
}
