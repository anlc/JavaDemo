package annotation.demo;

public class Testable {

    public void execute() {
        System.out.printf("Executing...");
    }

    @Test
    void testExecute(){
        execute();
    }
}
