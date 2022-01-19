package pattern.design_pattern.creational_patterns.singleton;

import org.junit.Test;

/**
 * 单例模式
 * 枚举
 *
 * 参考：http://www.runoob.com/design-pattern/singleton-pattern.html
 */
public class EnumSingletonPattern {

    enum Singleton {

        INSTANCE;

        @Override
        public String toString() {
            return "enum singleton pattern";
        }
    }

    @Test
    public void testPattern() {
        System.out.println(Singleton.INSTANCE.toString());
    }
}
