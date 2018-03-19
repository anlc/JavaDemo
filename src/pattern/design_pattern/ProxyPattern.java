package pattern.design_pattern;

import org.junit.Test;

/**
 * 代理模式
 *
 * 参考：http://www.runoob.com/design-pattern/proxy-pattern.html
 * 源码参考：Android中：ActivityManagerProxy
 */
public class ProxyPattern {

    interface Image {
        void display();
    }

    public class RealImage implements Image {

        private String fileName;

        public RealImage(String fileName) {
            this.fileName = fileName;
            loadFromDisk(fileName);
        }

        @Override
        public void display() {
            System.out.println("display " + fileName);
        }

        private void loadFromDisk(String fileName) {
            System.out.println("loading " + fileName);
        }
    }

    public class PoxyImage implements Image{

        private RealImage image;
        private String fileName;

        public PoxyImage(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (image == null) {
                image = new RealImage(fileName);
            }
            image.display();
        }
    }

    @Test
    public void testPattern(){
        Image image = new PoxyImage("test.jpg");

        image.display();
        image.display();
    }
}
