package pattern.head_first.structural;

public class FacadePattern {

    public static class Curtain {
        void close() {
            System.out.println("Curtain close");
        }
    }

    public static class TV {
        void close() {
            System.out.println("TV close");
        }
    }

    public static class Light {
        void off() {
            System.out.println("Light off");
        }
    }

    public static class AIDevicesFacade {

        /**
         * 持有所有设备的引用
         */
        private Curtain curtain;
        private TV tv;
        private Light light;

        public AIDevicesFacade(Curtain curtain, TV tv, Light light) {
            this.curtain = curtain;
            this.tv = tv;
            this.light = light;
        }

        public void sleep() {
            // 将所有的设备关闭
            curtain.close();
            tv.close();
            light.off();
        }
    }

    public static void main(String[] args) {
        // 通过一个指令：睡觉，关闭所有的设备
        AIDevicesFacade facade = new AIDevicesFacade(new Curtain(), new TV(), new Light());
        facade.sleep();
    }
}
