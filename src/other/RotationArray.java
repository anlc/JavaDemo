package other;

import java.util.Arrays;

public class RotationArray {

    static void rotateYUV240SP(byte[] src, byte[] dst, int width, int height) {
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                dst[k] = src[width * j + i];
                k++;
            }
        }
        System.out.println(Arrays.toString(dst));
    }

    static void rotateYUV240SP90(byte[] src, byte[] dst, int width, int height) {
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = height - 1; j >= 0; j--) {
                dst[k] = src[width * j + i];
                k++;
            }
        }
        System.out.println(Arrays.toString(dst));
    }

    static void rotateYUV240SP270(byte[] src, byte[] dst, int width, int height) {
        int k = 0;
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                dst[k] = src[width * j - i];
                k++;
            }
        }
        System.out.println(Arrays.toString(dst));
    }

    static void rotateUV(byte[] src, byte[] dst, int width, int height) {
        int k = 0;
        for (int i = 1; i <= width / 2; i++) {
            for (int j = 1; j <= height / 2; j += 2) {
                dst[k] = src[width * j - i];
                k++;
                dst[k] = src[width * j - i];
                k++;
            }
        }
        System.out.println(Arrays.toString(dst));
    }

    public static void main(String[] args) {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        byte[] dst = new byte[data.length];
//        rotateYUV240SP(data, dst, 3, 3);
        rotateYUV240SP90(data, dst, 4, 2);
//        rotateYUV240SP270(data, dst, 3, 3);
    }
}
