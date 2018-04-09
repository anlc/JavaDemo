package pattern.design_pattern;

import org.junit.Test;

/**
 * 外观模式
 *
 * 参考：http://www.runoob.com/design-pattern/facade-pattern.html
 * 例如：第三方SDK，使用同一个类，对外暴露接口
 */
public class FacadePattern {

    interface Shape{
        void draw();
    }

    class Circle implements Shape{

        @Override
        public void draw() {
            System.out.println("Circle::draw()");
        }
    }

    class Square implements Shape{

        @Override
        public void draw() {
            System.out.println("Circle::draw()");
        }
    }

    class ShapeMaker{
        private Shape circle;
        private Shape square;

        public ShapeMaker() {
            circle = new Circle();
            square = new Square();
        }

        void drawCircle(){
            circle.draw();
        }

        void drawSquare(){
            square.draw();
        }
    }

    @Test
    public void testPattern(){
        ShapeMaker maker = new ShapeMaker();
        maker.drawCircle();
        maker.drawSquare();
    }
}
