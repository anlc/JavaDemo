package other;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class TestSort {

    public static void main(String[] args) {
//        List<String> fileList = new ArrayList<>();
//        fileList.add("plan.v2.yml");
//        fileList.add("plan.v1.yml");
//        System.out.println(fileList);
//        fileList.sort(String::compareTo);
//        System.out.println(fileList);
//        double total = 1000;
//        long available = 90;
//
//        double x = available / total * 100;
//        System.out.println("x:" + x + "::" + (x < 10));

//        new TestSort().test();
//        new TestSort().testByte();
//        System.out.println();
        TestSort testSort = new TestSort();
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        for (int i = 0; i < 5; i++) {
            testSort.test(data);
        }
    }

    public void testByte() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int ySize = data.length / 2;

        byte[] y = new byte[ySize];
        byte[] u = new byte[ySize / 2];
        byte[] v = new byte[ySize / 2];

        System.arraycopy(data, 0, y, 0, ySize);
        for (int i = ySize; i < data.length; i += 2) {
            u[(i - ySize) / 2] = data[i];
            v[(i - ySize) / 2] = data[i + 1];
        }

        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(y));
        System.out.println(Arrays.toString(u));
        System.out.println(Arrays.toString(v));
    }

    private ByteBuffer[] yuv = new ByteBuffer[3];

    public void test(byte[] data) {
        int ySize = data.length / 2;
        int capacity = data.length / 4;

        yuv[0] = ByteBuffer.allocate(ySize).put(data, 0, ySize);
        yuv[1] = ByteBuffer.allocate(capacity);
        yuv[2] = ByteBuffer.allocate(capacity);

        for (int i = ySize; i < data.length; i += 2) {
            yuv[1].put(data[i]);
            yuv[2].put(data[i + 1]);
        }

        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(yuv[0].array()));
        System.out.println(Arrays.toString(yuv[1].array()));
        System.out.println(Arrays.toString(yuv[2].array()));
    }

    public void testBuffer(byte[] data){

        int ySize = data.length / 2;
        int capacity = data.length / 4;

        byte[] ret = new byte[data.length];
        int total = ySize;

        ByteBuffer bufferY = ByteBuffer.wrap(ret, 0, total);
        ByteBuffer bufferU = ByteBuffer.wrap(ret, total, total / 2);
        ByteBuffer bufferV = ByteBuffer.wrap(ret, total + total / 2, total / 2);

        bufferY.put(data, 0, total);
        for (int i = total; i < data.length; i += 2) {
            bufferV.put(data[i]);
            bufferU.put(data[i + 1]);
        }

        System.out.println(Arrays.toString(ret));
        System.out.println(Arrays.toString(bufferY.array()));
        System.out.println(Arrays.toString(bufferU.array()));
        System.out.println(Arrays.toString(bufferV.array()));
    }
}
