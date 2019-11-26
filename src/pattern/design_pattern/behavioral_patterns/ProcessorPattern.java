package pattern.design_pattern.behavioral_patterns;

public class ProcessorPattern {

    public interface ResponseProcessor {
        boolean process();
    }

    public static class CommandProcessor implements ResponseProcessor {

        @Override
        public boolean process() {
            return true;
        }
    }

    public static class DcsProcessor implements ResponseProcessor {

        @Override
        public boolean process() {
            return false;
        }
    }

    public static class Manager{
        CommandProcessor commandProcessor = new CommandProcessor();
        DcsProcessor dcsProcessor = new DcsProcessor();

        private void testProcessor(){
            if (commandProcessor.process()) {
                return;
            }
            dcsProcessor.process();
        }
    }
}
