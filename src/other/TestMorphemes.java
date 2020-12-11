package other;

public class TestMorphemes {

    public static void main(String[] args) {
        String src = "lakdjflksjdfijfafjladjfi";
        String dst = "fjladjfilakdjflksjdfijfa";

//        int[] map = new int[256];
        int srcCount = 0;
        for (int i = 0; i < src.length(); i++) {
//            System.out.println("char: " + src.charAt(i));
//            System.out.println("arrays: " + ((int)src.charAt(i)));
//            System.out.println("arrays: " + map[((int)src.charAt(i))]);
//            System.out.println("arrays: " + map[src.charAt(i)]);
//            map[src.charAt(i)]++;
            srcCount += src.charAt(i);
        }
        int dstCount = 0;
        for (int i = 0; i < dst.length(); i++) {
//            if (map[dst.charAt(i)]-- == 0) {
//                System.out.println("不是变形词");
//                return;
//            }
            dstCount += dst.charAt(i);
        }
        if (srcCount == dstCount) {
            System.out.println("是变形词");
        }else{
            System.out.println("不是变形词");
        }
    }
}
