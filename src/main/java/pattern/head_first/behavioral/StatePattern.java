package pattern.head_first.behavioral;

public class StatePattern {

    public interface State {
        void insertQuarter();

        void ejectQuarter();

        void turnCrank();

        void dispense();
    }

    public static class GumballMachine {

        private State soleOutState;
        private State noQuarterState;
        private State hasQuarterState;
        private State soleState;

        private State state = soleOutState;
        private int count;

        public GumballMachine(int numberGumballs) {
            if (numberGumballs > 0) {
                state = noQuarterState;
            }
        }

        public void insertQuarter() {
            state.insertQuarter();
        }

        public void ejectQuarter() {
            state.ejectQuarter();
        }

        public void turnCrank() {
            state.turnCrank();
            state.dispense();
        }

        public void setState(State state) {
            this.state = state;
        }

        public void releaseBall() {
            System.out.println("A gumball comes rolling out the slot...");
            if (count != 0) {
                count--;
            }
        }

        public State getSoleOutState() {
            return soleOutState;
        }

        public State getNoQuarterState() {
            return noQuarterState;
        }

        public State getHasQuarterState() {
            return hasQuarterState;
        }
    }

    public static class NoQuarterState implements State {

        private GumballMachine mGumballMachine;

        public NoQuarterState(GumballMachine gumballMachine) {
            this.mGumballMachine = gumballMachine;
        }

        @Override
        public void insertQuarter() {
            System.out.println("You insert a quarter");
            mGumballMachine.setState(mGumballMachine.getHasQuarterState());
        }

        @Override
        public void ejectQuarter() {

        }

        @Override
        public void turnCrank() {

        }

        @Override
        public void dispense() {

        }
    }

    public static class SoldState implements State {

        @Override
        public void insertQuarter() {

        }

        @Override
        public void ejectQuarter() {

        }

        @Override
        public void turnCrank() {

        }

        @Override
        public void dispense() {

        }
    }
}
