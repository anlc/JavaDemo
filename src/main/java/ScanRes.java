import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ScanRes {

    static Set<String> valuesList = new TreeSet<>();

    public static void main(String[] args) {
        File rootDir = new File("");
        enterDir(rootDir);
        System.out.println("==== success ====");
        System.out.println(Arrays.toString(valuesList.toArray()));
        System.out.println("==== success ====");
    }

    private static void enterDir(File file) {
        File[] fileList = file.listFiles();
        if (fileList == null) {
            System.out.println("error file path: " + file.getAbsolutePath());
            return;
        }
        for (File subFile : fileList) {
            if (subFile.isDirectory()) {
                enterDir(subFile);
            } else {
                scanFile(subFile);
            }
        }
    }

    private static void scanFile(File file) {
        if (readFileLine(file)) {
            String path = file.getAbsolutePath();
            if (path.contains("values-")) {
                System.out.println("not file: " + path);
            } else {
                valuesList.add(path);
            }
        }
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
