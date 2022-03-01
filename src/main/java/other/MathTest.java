package other;

public class MathTest {
    public static void main(String[] args) {
        float ratio = 2.099f;
        int result = (int) Math.floor(ratio * 10);
        System.out.println(result + ", " + (result % 10 == 0));
        if (result % 10 == 0) {
            System.out.println(result / 10);
        } else {
            System.out.println(result / 10f);
        }
    }
}
