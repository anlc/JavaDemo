package other;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行命令
 */
public class CmdExecutor {

    public static List<String> execute(String path, String cmd) {
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = null;
        Process exec = null;
        try {
            exec = Runtime.getRuntime().exec(cmd, new String[0], new File(path));
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("命令执行失败了：" + cmd);
        } finally {
            if (exec != null) {
                exec.destroy();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("命令执行完，流关闭出错了：" + cmd);
            }
        }
        return lines;
    }


    public static String executeGetString(String path, String cmd) {
        List<String> list = CmdExecutor.execute(path, cmd);
        String ret = String.join("\n", list.toArray(new String[0]));
        return ret;
    }
}
