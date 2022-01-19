package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 *
 * 参考：http://www.runoob.com/design-pattern/memento-pattern.html
 * Android源码设计模式解析与实战 250页
 */
public class MementoPattern {

    class Memento {
        String state;

        public Memento(String state) {
            this.state = state;
        }
    }

    class Originator {
        String state;

        public Memento saveStateToMemento() {
            return new Memento(state);
        }

        public void getStateFromMemento(Memento memento) {
            state = memento.state;
        }
    }

    class CareTaker {
        List<Memento> mementoList = new ArrayList();

        void addMemento(Memento memento) {
            mementoList.add(memento);
        }

        Memento getMemento(int index) {
            return mementoList.get(index);
        }
    }

    @Test
    public void testPattern() {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.state = "State #1";
        originator.state = "State #2";
        careTaker.addMemento(originator.saveStateToMemento());
        originator.state = "State #3";
        careTaker.addMemento(originator.saveStateToMemento());
        originator.state = "State #4";

        System.out.println("Current State: " + originator.state);
        originator.getStateFromMemento(careTaker.getMemento(0));
        System.out.println("First saved State: " + originator.state);
        originator.getStateFromMemento(careTaker.getMemento(1));
        System.out.println("Second saved State: " + originator.state);
    }
}
