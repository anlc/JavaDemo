package pattern.design_pattern.structural_patterns;

import org.junit.Test;

/**
 * 装饰器模式
 *
 * 参考：http://www.runoob.com/design-pattern/decorator-pattern.html
 */
public class DecoratorPattern {

    interface Shape {
        void draw();
    }

    class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }

    class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    abstract class ShapeDecorator implements Shape {
        protected Shape decoratorShape;

        ShapeDecorator(Shape decoratorShape) {
            this.decoratorShape = decoratorShape;
        }

        @Override
        public void draw() {
            decoratorShape.draw();
        }
    }

    class RedShapeDecorator extends ShapeDecorator {

        RedShapeDecorator(Shape decoratorShape) {
            super(decoratorShape);
        }

        @Override
        public void draw() {
            decoratorShape.draw();
            setRedBorder(decoratorShape);
        }

        private void setRedBorder(Shape decoratedShape) {
            System.out.println("Border Color: Red");
        }
    }

    @Test
    public void testPattern() {
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        circle.draw();
        System.out.println();
        redCircle.draw();
        System.out.println();
        redRectangle.draw();
    }
}
