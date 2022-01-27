package pattern.head_first.behavioral;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    /**
     * 定义一个主题
     * 主题包含了状态：mValue
     * 当状态改变时，通知所有观察者
     */
    public static class Subject {

        private final List<Observer> mObserverList = new ArrayList<>();
        private int mValue;

        /**
         * 注册观者者
         */
        public void registerObserver(Observer observer) {
            mObserverList.add(observer);
        }

        /**
         * 解注册观者者
         */
        public void unregisterObserver(Observer observer) {
            mObserverList.remove(observer);
        }

        /**
         * 更新状态
         */
        public void setValue(int value) {
            this.mValue = value;

            // 通知所有观察者状态更新
            notifyObservers();
        }

        /**
         * 通知所有观者者状态更新
         */
        public void notifyObservers() {
            for (Observer observer : mObserverList) {
                observer.update(mValue);
            }
        }
    }

    /**
     * 定义一个接收状态改变的观察者接口
     */
    public interface Observer {
        void update(int value);
    }

    public static void main(String[] args) {
        Subject subject = new Subject();

        // 注册两个观者者
        subject.registerObserver(value -> System.out.println("observer 1 received: " + value));
        subject.registerObserver(value -> System.out.println("observer 2 received: " + value));

        // 更改状态
        subject.setValue(1);
    }
}
