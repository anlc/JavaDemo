package concurrenttest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FutureRender {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    void renderPage(String source) throws ExecutionException, InterruptedException {

        List<String> imageList = scanImageUrlFromSource(source);
        Callable<List<ImageData>> callable = () -> {
            // 串行的创建对象
            List<ImageData> list = new ArrayList<>();
            for (String string : imageList) {
                list.add(new ImageData(string));
            }
            return list;
        };
        Future<List<ImageData>> submit = executor.submit(callable);
        List<ImageData> imageDataList = submit.get();
        renderText(source);

        for (ImageData item : imageDataList) {
            item.load();
        }
        executor.shutdown();
    }

    private void renderText(String source) {
        System.out.println("renderText source ---- ");
    }

    private List<String> scanImageUrlFromSource(String source) {
        return Arrays.asList("123", "456", "123", "456");
    }

    static class ImageData {
        String data;

        public ImageData(String data) {
            this.data = data;
            System.out.println(" ImageData: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void load() {
            System.out.println("load --> " + data);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new FutureRender().renderPage("123");
    }
}
