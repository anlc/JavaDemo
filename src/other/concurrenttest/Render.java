package other.concurrenttest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Render {

    private ExecutorService service = Executors.newFixedThreadPool(10);

    public void renderPage(String source) throws InterruptedException, ExecutionException {
        List<String> imgList = scanImageUrlFromSource(source);
//        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(service);
//        for (String string : imgList) {
//            // 并行的提交多个任务
//            completionService.submit(() -> new ImageData(string));
//        }
//
//        for (int i = 0; i < imgList.size(); i++) {
//            // 并行的获取每个任务完成的结果
//            Future<ImageData> take = completionService.take();
//            take.get().load();
//        }

        List<Callable<ImageData>> list = new ArrayList<>();
        for (String string : imgList) {
            list.add(() -> new ImageData(string));
        }
        List<Future<ImageData>> futures = service.invokeAll(list, 2, TimeUnit.SECONDS);
        for (Future<ImageData> future : futures) {
            // 并行的获取每个任务完成的结果
            future.get().load();
        }
        service.shutdown();
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
            System.out.println(" load: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Render().renderPage("23");
    }
}
