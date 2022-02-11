package pattern.head_first.behavioral;

public class CommandPattern {

    public interface Command {

        /**
         * 执行命令
         */
        void execute();

        /**
         * 撤销命令
         */
        void undo();
    }

    public static class Light {
        public void on() {
            System.out.println("light on");
        }

        public void off() {
            System.out.println("light off");
        }
    }

    /**
     * 命令开
     */
    public static class LightOnCommand implements Command {

        private Light light;

        /**
         * 传入具体设备，以便让这个命令控制
         */
        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            // 执行具体命令开
            light.on();
        }

        @Override
        public void undo() {
            light.off();
        }
    }

    /**
     * 命令关
     */
    public static class LightOffCommand implements Command {

        private Light light;

        /**
         * 传入具体设备，以便让这个命令控制
         */
        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            // 执行具体命令关
            light.off();
        }

        @Override
        public void undo() {
            light.on();
        }
    }

    public static class SimpleRemoteControl {

        /**
         * 数组记录多个命令
         */
        private Command[] command;

        public SimpleRemoteControl() {
            command = new Command[2];
        }

        public void setCommand(int index, Command command) {
            this.command[index] = command;
        }

        public void buttonWasPressed(int index) {
            // 调用命令执行, 不关心具体是什么命令
            command[index].execute();
        }
    }

    public static void main(String[] args) {
        // 实例化控制器，并添加两个具体命令
        SimpleRemoteControl remoteControl = new SimpleRemoteControl();
        remoteControl.setCommand(0, new LightOnCommand(new Light()));
        remoteControl.setCommand(1, new LightOffCommand(new Light()));

        // 模拟按下按键，调用命令执行
        remoteControl.buttonWasPressed(0);
        remoteControl.buttonWasPressed(1);
    }
}
