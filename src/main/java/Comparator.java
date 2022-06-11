import java.io.*;
import java.util.*;

public class Comparator {

    public static void main(String[] args) {
        File file = new File("");
        File[] files = file.listFiles();
        List<String> valuesList = new ArrayList<>();
        Arrays.sort(files);
        for (int i = 0; i < files.length; i++) {
            File dir = files[i];
            if (!dir.isDirectory()) {
                continue;
            }
            File[] listFiles = dir.listFiles();
            System.out.println(Arrays.toString(listFiles));
            for (int j = 0; j < listFiles.length; j++) {
                if (readFileLine(listFiles[j])) {
                    valuesList.add(dir.getName());
                    break;
                }
            }
        }
        System.out.println("========");
        System.out.println(Arrays.toString(valuesList.toArray()));

        System.out.println("-----------------");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < valuesList.size(); i++) {
            String item = valuesList.get(i);
            item = item.replace("values-", "");
//            System.out.println(item);
            builder.append(item);
            builder.append(",");
        }
        String string = builder.toString();
        System.out.println(string);

    }

    private static boolean readFileLine(File file) {
        try (BufferedReader targetStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String lineTxt;
            while ((lineTxt = targetStream.readLine()) != null) {
                if (lineTxt.contains("string name=")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
