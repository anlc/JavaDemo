package pattern.design_pattern.creational_patterns.factory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册工厂模式
 *
 * 参考：Java 编程思想  582页
 */
public class RegisterFactoryPattern {

    interface Factory<T> {
        T create();
    }

    static class Part {
        @Override
        public String toString() {
            return getClass().getSimpleName();
        }

        static List<Factory<? extends Part>> partFactories = new ArrayList<>();

        static {
            partFactories.add(new FuelFilter.Factory());
            partFactories.add(FanBelt::new);
        }

        public static Part create(int position) {
            return partFactories.get(position).create();
        }
    }

    static class Filter extends Part {}

    static class FuelFilter extends Filter {
        public static class Factory implements RegisterFactoryPattern.Factory<FuelFilter> {
            @Override
            public FuelFilter create() {
                return new FuelFilter();
            }
        }
    }

    static class Belt extends Part {}

    static class FanBelt extends Belt implements Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }

    @Test
    public void testPattern() {
        for (int i = 0; i < Part.partFactories.size(); i++) {
            System.out.println(Part.create(i));
        }
    }
}
