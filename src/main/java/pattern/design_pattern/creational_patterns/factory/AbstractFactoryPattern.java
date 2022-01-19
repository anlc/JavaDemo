package pattern.design_pattern.creational_patterns.factory;

import org.junit.Test;

/**
 * 抽象工厂模式
 *
 * 参考：http://www.runoob.com/design-pattern/abstract-factory-pattern.html
 */
public class AbstractFactoryPattern {

    interface Shape{
        void draw();
    }

    static class Circle implements Shape{

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    interface Color{
        void fill();
    }

    static class Red implements Color{

        @Override
        public void fill() {
            System.out.println("Inside Red::fill() method.");
        }
    }

    static abstract class AbstractFactory{
        abstract Shape getShape(String shape);

        abstract Color getColor(String color);
    }

    static class ShapeFactory extends AbstractFactory{

        @Override
        Shape getShape(String shape) {
            if (shape.equals("circle")) {
                return new Circle();
            }
            return null;
        }

        @Override
        Color getColor(String color) {
            return null;
        }
    }

    static class ColorFactory extends AbstractFactory{

        @Override
        Shape getShape(String shape) {
            return null;
        }

        @Override
        Color getColor(String color) {
            if (color.equals("red")) {
                return new Red();
            }
            return null;
        }
    }

    static class FactoryProducer{
        static AbstractFactory getFactory(String choice) {
            if (choice.equals("color")) {
                return new ColorFactory();
            } else if (choice.equals("shape")) {
                return new ShapeFactory();
            }
            return null;
        }
    }

    @Test
    public void testPattern(){
        FactoryProducer.getFactory("shape").getShape("circle").draw();
        FactoryProducer.getFactory("color").getColor("red").fill();
    }
}
