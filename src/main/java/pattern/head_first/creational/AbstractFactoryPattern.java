package pattern.head_first.creational;

public class AbstractFactoryPattern {

    interface Pizza {
        void display();
    }

    public static abstract class PizzaStore {

        public Pizza orderPizza(String type) {
            Pizza pizza = createPizza(type);
            pizza.display();
            return pizza;
        }

        /**
         * 创建比萨由具体工厂实现
         */
        protected abstract Pizza createPizza(String type);
    }

    public static class NYStyleCheesePizza implements Pizza{
        @Override
        public void display() {
            System.out.println("NYStyleCheesePizza");
        }
    }

    public static class NYStyleClamPizza implements Pizza{
        @Override
        public void display() {
            System.out.println("NYStyleClamPizza");
        }
    }


    public static class ChicagoStyleCheesePizza implements Pizza{
        @Override
        public void display() {
            System.out.println("ChicagoStyleCheesePizza");
        }
    }

    public static class ChicagoStyleClamPizza implements Pizza{
        @Override
        public void display() {
            System.out.println("ChicagoStyleClamPizza");
        }
    }

    public static class NYPizzaStore extends PizzaStore {

        @Override
        protected Pizza createPizza(String type) {
            if ("ClamPizza".equals(type)) {
                return new NYStyleClamPizza();
            } else if ("CheesePizza".equals(type)) {
                return new NYStyleCheesePizza();
            }
            return null;
        }
    }

    public static class ChicagoPizzaStore extends PizzaStore {

        @Override
        protected Pizza createPizza(String type) {
            if ("ClamPizza".equals(type)) {
                return new ChicagoStyleClamPizza();
            } else if ("CheesePizza".equals(type)) {
                return new ChicagoStyleCheesePizza();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 通过不同地区的比萨店（具体工厂）创建各自风味的比萨
        PizzaStore nyPizzaStore = new NYPizzaStore();
        nyPizzaStore.orderPizza("ClamPizza");

        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        chicagoPizzaStore.orderPizza("ClamPizza");
    }
}
