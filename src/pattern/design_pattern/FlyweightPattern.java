package pattern.design_pattern;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 *
 * 参考：http://www.runoob.com/design-pattern/flyweight-pattern.html
 */
public class FlyweightPattern {

    interface Shape {
        void draw();
    }

    static class Circle implements Shape {

        String color;
        int x;
        int y;
        int radius;

        public Circle(String color) {
            this.color = color;
        }

        @Override
        public void draw() {
            System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius + "]");
        }
    }

    static class ShapeFactory {
        static final Map<String, Shape> map = new HashMap<>();

        static Circle getShape(String color) {
            Circle circle = (Circle) map.get(color);
            if (circle == null) {
                circle = new Circle(color);
                map.put(color, circle);
                System.out.println("Creating circle of color : " + color);
            }
            return circle;
        }
    }

    String colors[] = {"Red", "Green", "Blue", "White", "Black"};

    @Test
    public void testPattern() {
        for (int i = 0; i < 10; i++) {
            Circle circle = ShapeFactory.getShape(getRandomColor());
            circle.x = getRandomX();
            circle.y = getRandomY();
            circle.radius = 100;
            circle.draw();
        }
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private int getRandomY() {
        return (int) (Math.random() * 100);
    }

}
