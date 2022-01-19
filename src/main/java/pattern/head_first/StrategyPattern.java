package pattern.head_first;

public class StrategyPattern {

    /**
     * 鸭子飞的行为
     */
    public interface FlyBehavior {
        void fly();
    }

    public static class FlyWithWings implements FlyBehavior {

        @Override
        public void fly() {
            System.out.println("fly with wings");
        }
    }

    public static class FlyNotWay implements FlyBehavior {

        @Override
        public void fly() {
            System.out.println("can't fly");
        }
    }

    /**
     * 鸭子叫的行为
     */
    public interface QuackBehavior {
        void quack();
    }

    public static class Quack implements QuackBehavior {

        @Override
        public void quack() {
            System.out.println("quack");
        }
    }

    public static class Squeak implements QuackBehavior {

        @Override
        public void quack() {
            System.out.println("squeak");
        }
    }

    /**
     * 鸭子类型
     */
    public static abstract class Duck {
        FlyBehavior mFlyBehavior;
        QuackBehavior mQuackBehavior;

        /**
         * 替换飞的行为
         * @param flyBehavior
         */
        public void setFlyBehavior(FlyBehavior flyBehavior) {
            this.mFlyBehavior = flyBehavior;
        }

        /**
         * 替换呱呱叫的行为
         * @param quackBehavior
         */
        public void setQuackBehavior(QuackBehavior quackBehavior) {
            this.mQuackBehavior = quackBehavior;
        }

        /**
         * 执行飞的行为
         */
        public void preformFly(){
            mFlyBehavior.fly();
        }

        /**
         * 执行呱呱叫的行为
         */
        public void preformQuack(){
            mQuackBehavior.quack();
        }

        /**
         * 显示外观
         */
        public abstract void display();
    }

    public static class StupidDuck extends Duck{

        public StupidDuck() {
            // 初始化飞的行为
            mFlyBehavior = new FlyNotWay();

            // 初始化呱呱叫的行为
            mQuackBehavior = new Quack();
        }

        @Override
        public void display() {
            System.out.println("red head display");
        }
    }

    public static void main(String[] args) {
        Duck stupidDuck = new StupidDuck();

        // 一开始不会飞
        stupidDuck.preformFly();

        // 替换了飞的行为（算法）之后, 会飞了
        stupidDuck.setFlyBehavior(new FlyWithWings());
        stupidDuck.preformFly();

        stupidDuck.preformQuack();
        stupidDuck.display();
    }
}
