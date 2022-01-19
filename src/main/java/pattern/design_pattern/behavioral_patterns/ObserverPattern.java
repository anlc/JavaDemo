package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 观察者模式
 *
 * 参考：http://www.runoob.com/design-pattern/observer-pattern.html
 * 源码参考：BroadcastReceiver （Android源码设计模式解析与实战 230页）
 */
public class ObserverPattern {

    abstract class Observer {
        protected Subject subject;

        public Observer(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        abstract void update();
    }

    class Subject {
        List<Observer> observerList = new ArrayList<>();
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObserver();
        }

        public void attach(Observer observer) {
            observerList.add(observer);
        }

        public void notifyAllObserver() {
            for (Observer observer : observerList) {
                observer.update();
            }
        }
    }

    class BinaryObserver extends Observer {

        BinaryObserver(Subject subject) {
            super(subject);
        }

        @Override
        void update() {
            System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
        }
    }

    class OctalObserver extends Observer {

        OctalObserver(Subject subject) {
            super(subject);
        }

        @Override
        void update() {
            System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
        }
    }

    @Test
    public void testPattern() {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println();
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}


/**
 * 观察者模式
 *
 * 参考：https://www.w3cschool.cn/javadesignpattern/oplz1ihy.html
 */
class ObserverPattern2 {

    interface Observer {
        void update();
    }

    static abstract class Subject {
        Vector<Observer> vector = new Vector();

        void addObserver(Observer observer) {
            vector.add(observer);
        }

        void delObserver(Observer observer) {
            vector.remove(observer);
        }

        void notifyObserver(){
            for (Observer observer : vector) {
                observer.update();
            }
        }

        protected abstract void doSomething();
    }

    static class ConcreteSubject extends Subject{

        @Override
        protected void doSomething() {
            System.out.println("被观察者事件反生");
            this.notifyObserver();
        }
    }

    static class ConcreteObserver1 implements Observer{

        @Override
        public void update() {
            System.out.println("观察者1收到信息，并进行处理。");
        }
    }

    static class ConcreteObserver2 implements Observer{

        @Override
        public void update() {
            System.out.println("观察者2收到信息，并进行处理。");
        }
    }

    public static void main(String[] args){
        Subject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver1());
        subject.addObserver(new ConcreteObserver2());
        subject.doSomething();
    }
}
