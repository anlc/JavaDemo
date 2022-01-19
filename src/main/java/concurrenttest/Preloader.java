package concurrenttest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<>(() -> {
        System.out.println("future task called");
        return loadProductInfo();
    });

    private Thread thread = new Thread(future);

    private ProductInfo loadProductInfo() {
        System.out.println("loadProductInfo -- ");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ProductInfo("123");
    }

    public void start() {
        thread.start();
    }

    public ProductInfo get() {
        try {
            System.out.println("get -- ");
            return future.get(); // 会阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class ProductInfo {
        String name;

        public ProductInfo(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Preloader preloader = new Preloader();
        preloader.start();
        ProductInfo productInfo = preloader.get();
        System.out.println(productInfo.name);
    }
}
