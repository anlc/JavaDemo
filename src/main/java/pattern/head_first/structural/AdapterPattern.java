package pattern.head_first.structural;

public class AdapterPattern {

    interface Duck {
        /**
         * 鸭子叫
         */
        void quack();
    }

    interface Turkey {
        /**
         * 火鸡叫
         */
        void gobble();
    }

    public static class MallardDuck implements Duck {

        @Override
        public void quack() {
            System.out.println("MallardDuck quack");
        }
    }

    public static class WildTurkey implements Turkey {

        @Override
        public void gobble() {
            System.out.println("WildTurkey gobble");
        }
    }

    public static class TurkeyAdapter implements Duck {

        /**
         * 持有一个火鸡
         */
        private Turkey turkey;

        public TurkeyAdapter(Turkey turkey) {
            this.turkey = turkey;
        }

        @Override
        public void quack() {
            // 装换为调用火鸡叫
            turkey.gobble();
        }
    }

    public static void main(String[] args) {
        // 一个真的鸭子叫
        Duck duck = new MallardDuck();
        duck.quack();

        // 一个适配成鸭子的火鸡叫
        Duck dummyDck = new TurkeyAdapter(new WildTurkey());
        dummyDck.quack();
    }
}
