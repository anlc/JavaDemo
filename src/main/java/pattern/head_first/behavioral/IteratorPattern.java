package pattern.head_first.behavioral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IteratorPattern {

    public interface Iterator {
        boolean hasNext();

        Object next();
    }

    public static class MenuItem {
        String name;
        double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static class PancakeHouseMenu {
        List<MenuItem> menuItems;

        public PancakeHouseMenu() {
            menuItems = new ArrayList<>();
            menuItems.add(new MenuItem("Regular pancake breakfast", 2.99));
            menuItems.add(new MenuItem("Blueberry pancake", 3.99));
        }

        public List<MenuItem> getMenuItems() {
            return menuItems;
        }
    }

    public static class DinerMenu {
        MenuItem[] menuItems;
        private int position;

        public DinerMenu() {
            this.menuItems = new MenuItem[]{
                    new MenuItem("Vegetarian BLT", 2.99),
                    new MenuItem("Soup of the day", 1.99)
            };
        }

        public MenuItem[] getMenuItems() {
            return menuItems;
        }
    }

    public static class DinerMenuIterator implements Iterator {

        MenuItem[] menuItems;
        private int position;

        public DinerMenuIterator(MenuItem[] menuItems) {
            this.menuItems = menuItems;
        }

        @Override
        public boolean hasNext() {
            return position < menuItems.length && menuItems[position] != null;
        }

        @Override
        public Object next() {
            MenuItem item = menuItems[position];
            position = position + 1;
            return item;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DinerMenu().getMenuItems()));
        System.out.println(Arrays.toString(new PancakeHouseMenu().getMenuItems().toArray()));

        DinerMenu dinerMenu = new DinerMenu();
        MenuItem[] menuItems = dinerMenu.getMenuItems();
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }

        DinerMenuIterator dinerMenuIterator = new DinerMenuIterator(menuItems);
        while (dinerMenuIterator.hasNext()) {
            System.out.println(dinerMenuIterator.next());
        }

        PancakeHouseMenu houseMenu = new PancakeHouseMenu();
        List<MenuItem> houseMenuItems = houseMenu.getMenuItems();
        for (MenuItem item : houseMenuItems) {
            System.out.println(item);
        }

    }

}
