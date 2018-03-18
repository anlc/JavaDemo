package pattern.design_pattern;

/**
 * 状态模式
 * 参考：
 * http://www.runoob.com/design-pattern/state-pattern.html
 *
 * 登陆状态，可根据使用状态模式来控制对应操作
 * 源码参考：android中WI-FI管理，WifiSettings;
 */
public class StatePattern {

    static class Context{
        public State state;
    }

    interface State{
        void doAction(Context context);
    }

    static class StartState implements State{

        @Override
        public void doAction(Context context) {
            System.out.println("player is in start state");
            context.state = this;
        }

        @Override
        public String toString() {
            return "start state";
        }
    }

    static class StopState implements State{

        @Override
        public void doAction(Context context) {
            System.out.println("player is in stop state");
            context.state = this;
        }

        @Override
        public String toString() {
            return "stop state";
        }
    }

    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.state.toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.state.toString());
    }
}
