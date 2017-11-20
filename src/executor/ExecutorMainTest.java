package executor;

/**
 * Created by n on 2017/11/20.
 */
public class ExecutorMainTest {

    public static void main(String[] args) {
        ExecutorMainTest test = new ExecutorMainTest();

//        test.test("cache");
//        test.test("fixed");
        test.test("single");
    }

    public void test(String type) {
        ExecutorDemo demo = new ExecutorDemo(type);
        for (int i = 0; i < 5; i++) {
//            demo.execute(new RunnableClass());
            demo.submit(new RunnableClass());
        }
        demo.release();
    }
}
