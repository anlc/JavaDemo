package executor;

/**
 * Created by n on 2017/11/20.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorTest test = new ExecutorTest();

//        test.test("cache");
//        test.test("fixed");
//        test.test("single");
        test.test("custom");
    }

    public void test(String type) {
        ExecutorDemo demo = new ExecutorDemo(type);
        for (int i = 0; i < 5; i++) {
            demo.execute(new RunnableClass());
        }
        demo.release();
    }
}
