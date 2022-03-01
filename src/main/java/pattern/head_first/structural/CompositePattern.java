package pattern.head_first.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {

    public static abstract class MenuComponent {

        public void add(MenuComponent menuComponent) {
            throw new UnsupportedOperationException();
        }

        public void remove(MenuComponent menuComponent) {
            throw new UnsupportedOperationException();
        }

        public MenuComponent getChild(int i) {
            throw new UnsupportedOperationException();
        }

        public String getName() {
            throw new UnsupportedOperationException();
        }

        public String getDescription() {
            throw new UnsupportedOperationException();
        }

        public double getPrice() {
            throw new UnsupportedOperationException();
        }

        public void print() {
            throw new UnsupportedOperationException();
        }
    }

    public static class MenuItem extends MenuComponent {

        private String name;
        private double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public void print() {
            System.out.println(getName() + ", " + getPrice());
        }
    }

    public static class Menu extends MenuComponent {

        /**
         * 组合菜品
         */
        private List<MenuComponent> menuComponents = new ArrayList<>();
        private String name;
        private String description;

        public Menu(String name, String description) {
            this.name = name;
            this.description = description;
        }

        @Override
        public void add(MenuComponent menuComponent) {
            menuComponents.add(menuComponent);
        }

        @Override
        public void remove(MenuComponent menuComponent) {
            menuComponents.remove(menuComponent);
        }

        @Override
        public MenuComponent getChild(int i) {
            return menuComponents.get(i);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public void print() {
            System.out.println("\n" + getName() + ", " + getDescription());
            System.out.println("------------");
            for (MenuComponent menuComponent : menuComponents) {
                menuComponent.print();
            }
        }
    }

    public static class Waitress {
        MenuComponent allMenu;

        public Waitress(MenuComponent allMenu) {
            this.allMenu = allMenu;
        }

        public void printMenu() {
            allMenu.print();
        }
    }

    public static void main(String[] args) {

        // 定义各种类型的菜单
        MenuComponent houseMenu = new Menu("HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
        MenuComponent caffMenu = new Menu("CAFF MENU", "Dinner");
        MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course");
        MenuComponent allMenu = new Menu("ALL MENU", "All menu");

        // 组合菜单
        allMenu.add(houseMenu);
        allMenu.add(dinerMenu);
        allMenu.add(caffMenu);
        allMenu.add(dessertMenu);

        // 组合菜品
        houseMenu.add(new MenuItem("xxxxx", 3.89));
        houseMenu.add(new MenuItem("xxxxx", 3.89));
        dinerMenu.add(new MenuItem("Pasta", 3.89));
        dinerMenu.add(new MenuItem("Pasta", 3.89));
        dinerMenu.add(dessertMenu);
        dessertMenu.add(new MenuItem("Apple pie", 1.59));
        dessertMenu.add(new MenuItem("Apple pie", 1.59));

        // 测试输出
        new Waitress(allMenu).printMenu();
    }
}
