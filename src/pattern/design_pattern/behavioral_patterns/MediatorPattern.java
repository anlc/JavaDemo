package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

import java.util.Date;

/**
 * 中介者模式
 *
 * 参考：http://www.runoob.com/design-pattern/mediator-pattern.html
 * 源码参考：Keyguard锁屏功能的实现，Binder机制（Android源码设计模式解析与实战 324页）
 */
public class MediatorPattern {

    static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        void sendMessage(String message) {
            ChatRoom.sendMessage(this, message);
        }
    }

    static class ChatRoom {
        static void sendMessage(User user, String message) {
            System.out.println(new Date().toString() + " [" + user.name + "] : " + message);
        }
    }

    @Test
    public void testPattern() {
        User john = new User("John");
        User robert = new User("Robert");

        john.sendMessage("Hi Robert!");
        robert.sendMessage("Hello John!");
    }
}
