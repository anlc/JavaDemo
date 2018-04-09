package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 *
 * 参考：http://www.runoob.com/design-pattern/command-pattern.html
 */
public class CommandPattern {

    public interface Order {
        void execute();
    }

    public class Stock {
        private String name = "ABC";
        private int quantity = 10;

        public void buy() {
            System.out.println("Stock [ Name: " + name + ",Quantity: " + quantity + " ] bought");
        }

        public void sell() {
            System.out.println("Stock [ Name: " + name + ",Quantity:" + quantity + " ] sold");
        }
    }

    public class BuyStock implements Order{

        private Stock stock;

        public BuyStock(Stock stock) {
            this.stock = stock;
        }

        @Override
        public void execute() {
            stock.buy();
        }
    }

    public class SellStock implements Order{

        private Stock stock;

        public SellStock(Stock stock) {
            this.stock = stock;
        }

        @Override
        public void execute() {
            stock.sell();
        }
    }

    public class Broker{
        private List<Order> orderList = new ArrayList<>();

        public void takeOrder(Order order) {
            orderList.add(order);
        }

        public void placeOrder(){
            for (Order order : orderList) {
                order.execute();
            }
            orderList.clear();
        }
    }

    @Test
    public void testPattern(){
        Stock stock = new Stock();

        BuyStock buyStock = new BuyStock(stock);
        SellStock sellStock = new SellStock(stock);

        Broker broker = new Broker();
        broker.takeOrder(buyStock);
        broker.takeOrder(sellStock);
        broker.placeOrder();
    }
}
