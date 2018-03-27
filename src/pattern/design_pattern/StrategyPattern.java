package pattern.design_pattern;

import org.junit.Test;

/**
 * 策略模式
 *
 * 参考：http://www.runoob.com/design-pattern/strategy-pattern.html
 */
public class StrategyPattern {

    interface Strategy {
        int doOperation(int num1, int num2);
    }

    class OperationAdd implements Strategy {

        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }

    class OperationSub implements Strategy {

        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    class Context {
        private Strategy strategy;

        public Context(Strategy strategy) {
            this.strategy = strategy;
        }

        public int execute(int num1, int num2) {
            return strategy.doOperation(num1, num2);
        }
    }

    @Test
    public void testPattern() {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.execute(10, 5));

        context = new Context(new OperationSub());
        System.out.println("10 - 5 = " + context.execute(10, 5));
    }
}
