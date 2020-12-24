package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxDupliLength {

    public static void main(String[] args) {
        String src = "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba" +
                "abcdefghijklmnopqrstba";
        int length = getMaxDupliLength(src);
        System.out.println(length);
    }

    private static int getMaxDupliLength(String src) {
        long millis = System.currentTimeMillis();
        Map<Character, Character> temp = new HashMap<>();
        int maxSize = 0;
        int tempMaxSize = 0;
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (temp.get(c) == null) {
                tempMaxSize++;
            } else {
                maxSize = tempMaxSize > maxSize ? tempMaxSize : maxSize;
                tempMaxSize = 0;
                temp.clear();
            }
            temp.put(c, c);
        }
        System.out.println(System.currentTimeMillis() - millis);
        return maxSize;
    }

    private static int getMaxDupliLength2(String src) {
        long millis = System.currentTimeMillis();
        String subString = new String();
        int maxSize = 0;
        int tempMaxSize = 0;
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (subString.contains(String.valueOf(c))) {
                maxSize = tempMaxSize > maxSize ? tempMaxSize : maxSize;
                tempMaxSize = 0;
                subString = "";
            } else {
                tempMaxSize++;
            }
            subString += c;
        }
        System.out.println(System.currentTimeMillis() - millis);
        return maxSize;
    }
}
