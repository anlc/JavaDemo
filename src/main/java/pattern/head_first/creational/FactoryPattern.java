package pattern.head_first.creational;

public class FactoryPattern {

    interface Pizza {

        /**
         * 制作比萨有很多步骤, 只简化成一步
         */
        void display();
    }

    public static class CheesePizza implements Pizza {
        @Override
        public void display() {
            System.out.println("CheesePizza");
        }
    }

    public static class ClamPizza implements Pizza {
        @Override
        public void display() {
            System.out.println("ClamPizza");
        }
    }

    public static class SimplePizzaFactory {
        public Pizza createPizza(String type) {
            if ("ClamPizza".equals(type)) {
                return new FactoryPattern.ClamPizza();
            } else if ("CheesePizza".equals(type)) {
                return new FactoryPattern.CheesePizza();
            }
            return null;
        }
    }

    public static class PizzaStore {
        private SimplePizzaFactory mSimplePizzaFactory;

        public PizzaStore(SimplePizzaFactory factory) {
            this.mSimplePizzaFactory = factory;
        }

        public Pizza orderPizza(String type) {
            // 具体的比萨类型由工厂创建
            Pizza pizza = mSimplePizzaFactory.createPizza(type);
            pizza.display();
            return pizza;
        }
    }

    public static void main(String[] args) {
        // 创建一个比萨店, 给它一个创建比萨的工厂
        PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());

        // 可以点不同口味的比萨了
        pizzaStore.orderPizza("CheesePizza");
        pizzaStore.orderPizza("ClamPizza");
    }
}
