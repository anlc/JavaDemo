package pattern.head_first.structural;

public class DecoratorPattern {

    public static abstract class Beverage {

        String description = "unknown beverage";

        /**
         * 提供描述信息
         */
        public String getDescription() {
            return description;
        }

        /**
         * 计算价格
         */
        public abstract double cost();
    }

    public static class Espresso extends Beverage {

        public Espresso() {
            // 初始化描述信息
            description = "espresso";
        }

        /**
         * @return 返回自身价格
         */
        @Override
        public double cost() {
            return 1.99d;
        }
    }

    public static class HouseBlend extends Beverage {

        public HouseBlend() {
            // 初始化描述信息
            description = "house blend coffee";
        }

        /**
         * @return 返回自身价格
         */
        @Override
        public double cost() {
            return 0.89d;
        }
    }

    public static abstract class CondimentDecorator extends Beverage {

        /**
         * 希望具体的装饰者可以添加具体的信息
         */
        public abstract String getDescription();
    }

    public static class Mocha extends CondimentDecorator {

        /**
         * 记录被装饰者
         */
        private Beverage mBeverage;

        public Mocha(Beverage beverage) {
            this.mBeverage = beverage;
        }

        @Override
        public double cost() {
            //  返回添加了自身的价格
            return mBeverage.cost() + 0.2;
        }

        @Override
        public String getDescription() {
            // 返回添加了自身的描述
            return mBeverage.getDescription() + ", mocha";
        }
    }

    public static void main(String[] args) {
        // 我们可以直接点一个具体类型的咖啡
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        // 也可以点 通过装饰者添加了各种调料 的咖啡
        Beverage houseBlend = new HouseBlend();
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Mocha(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());
    }
}
