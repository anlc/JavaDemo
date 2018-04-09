package pattern.design_pattern.creational_patterns;

import org.junit.Test;

import java.util.HashMap;

/**
 * 原型模式
 *
 * 参考：http://www.runoob.com/design-pattern/prototype-pattern.html
 * 源码参考：Calendar
 */
public class PrototypePattern {

    static abstract class Shape implements Cloneable {

        String type;

        abstract void draw();

        @Override
        protected Shape clone() {
            Shape shape = null;
            try {
                shape = (Shape) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return shape;
        }
    }

    static class Circle extends Shape {

        public Circle() {
            type = "circle";
        }

        @Override
        void draw() {
            System.out.println("Circle :: draw()");
        }
    }

    static class Square extends Shape {

        public Square() {
            type = "square";
        }

        @Override
        void draw() {
            System.out.println("Square :: draw()");
        }
    }

    static class ShapeCache{
        static HashMap<String, Shape> shapeMap = new HashMap<>();

        static Shape getShape(String shapeId){
            Shape shape = shapeMap.get(shapeId);
            return shape.clone();
        }

        static void load(){
            shapeMap.put("1", new Circle());
            shapeMap.put("2", new Square());
        }
    }

    @Test
    public void testPattern(){
        ShapeCache.load();

        ShapeCache.getShape("1").draw();
        ShapeCache.getShape("2").draw();
    }
}
