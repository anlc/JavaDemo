package pattern.design_pattern.structural_patterns;

import org.junit.Test;

/**
 * 桥接模式
 *
 * 参考：
 * http://www.runoob.com/design-pattern/adapter-pattern.html
 * https://github.com/simple-android-framework/android_design_patterns_analysis/tree/master/bridge/shen0834
 */
public class BridgePattern {

    public interface DrawApi {
        void drawCircle(int radius, int x, int y);
    }

    public class RedCircle implements DrawApi {

        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
        }
    }

    public class GreenCircle implements DrawApi {

        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", " + y + "]");
        }
    }

    public abstract class Shape {
        protected DrawApi drawApi;

        public Shape(DrawApi drawApi) {
            this.drawApi = drawApi;
        }

        protected abstract void draw();
    }

    public class Circle extends Shape {
        private int x, y, radius;

        public Circle(DrawApi drawApi, int radius, int x, int y) {
            super(drawApi);
            this.radius = radius;
            this.x = x;
            this.y = y;
        }

        @Override
        protected void draw() {
            drawApi.drawCircle(radius, x, y);
        }
    }

    @Test
    public void testPattern(){
        new Circle(new RedCircle(), 10, 100, 200).draw();
        new Circle(new GreenCircle(), 10, 100, 200).draw();
    }
}
