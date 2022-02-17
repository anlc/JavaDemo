package pattern.head_first.behavioral;

public class TemplatePattern {

    public static class Coffee extends CaffeineBeverage {

        @Override
        public void brew() {
            System.out.println("Dripping coffee through filter");
        }

        @Override
        public void addCondiments() {
            System.out.println("Adding sugar and milk");
        }
    }

    public static class Tea extends CaffeineBeverage {

        @Override
        public void brew() {
            System.out.println("Dripping coffee through filter");
        }

        @Override
        public void addCondiments() {
            System.out.println("Adding lemon");
        }

        @Override
        public boolean costumerWantsCondiments() {
            // 子类控制模板方法流程
            return false;
        }
    }

    public static abstract class CaffeineBeverage {

        /**
         * 模板方法调用所有的步骤
         */
        final void prepareRecipe() {
            boilWater();
            brew();
            pourInCup();
            if (costumerWantsCondiments()) {
                addCondiments();
            }
        }

        public abstract void brew();

        public abstract void addCondiments();

        public void boilWater() {
            System.out.println("Boil water");
        }

        public void pourInCup() {
            System.out.println("Pouring in cup");
        }

        /**
         * 控制模板方法的钩子
         * 常常在使用模板模式时，用来控制模板方法的流程
         */
        public boolean costumerWantsCondiments() {
            return true;
        }
    }

    public static void main(String[] args) {
        // 制作一杯咖啡
        new Coffee().prepareRecipe();
        System.out.println();

        // 制作一杯茶
        new Tea().prepareRecipe();
    }
}
