package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

/**
 * 责任链模式
 *
 * 参考：http://www.runoob.com/design-pattern/chain-of-responsibility-pattern.html
 * 源码参考：Android中对事件分发处理（Android源码设计模式解析与实战 173页）
 */
public class ChainOfResponsibilityPattern {

    static abstract class AbstractLogger {
        public static int INFO = 1;
        public static int ERROR = 2;

        protected int level;
        protected AbstractLogger nextLogger;

        void logMsg(int level, String message) {
            if (this.level <= level) {
                write(message);
            }
            if (nextLogger != null) {
                nextLogger.write(message);
            }
        }

        abstract protected void write(String message);
    }

    class ConsoleLogger extends AbstractLogger {

        public ConsoleLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Standard Console::Logger: " + message);
        }
    }

    class ErrorLogger extends AbstractLogger {

        public ErrorLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Error Console::Logger: " + message);
        }
    }

    AbstractLogger getLogger() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger infoLogger = new ConsoleLogger(AbstractLogger.INFO);
        errorLogger.nextLogger = infoLogger;
        return errorLogger;
    }

    @Test
    public void testPattern(){
        AbstractLogger logger = getLogger();
        logger.logMsg(AbstractLogger.INFO, "this is info");
        logger.logMsg(AbstractLogger.ERROR, "this is error");
    }
}
