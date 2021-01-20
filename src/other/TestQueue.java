package other;

import java.util.concurrent.ArrayBlockingQueue;

public class TestQueue {

    static class CameraManager {

        private Callback mCallback;

        interface Callback {
            void onNewFrame();
        }

        void setCallback(Callback callback) {
            this.mCallback = callback;
        }

        void doWithFrame() {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mCallback.onNewFrame();
        }
    }

    static class Recorder {

        CameraManager mCameraManager;
        ArrayBlockingQueue<String> mQueue = new ArrayBlockingQueue<>(3);
        long mGetLastFrameTime;

        public Recorder() {
            this.mCameraManager = new CameraManager();
            mCameraManager.setCallback(() -> {
                mQueue.add("item");
                mGetLastFrameTime = System.currentTimeMillis();
            });
            this.mCameraManager.doWithFrame();
        }

        long lastTime;
        int mCount;

        void read() {
            try {
                if (System.currentTimeMillis() - lastTime > 1000) {
                    System.out.println("count : " + mCount);
                    mCount = 0;
                    lastTime = System.currentTimeMillis();
                }
                mCount++;
                mQueue.take();
                long intervalTime = System.currentTimeMillis() - mGetLastFrameTime;
                long sleepTime = 45 - intervalTime;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
                mCameraManager.doWithFrame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Recorder recorder = new Recorder();
        new Thread(() -> {
            while (true) {
                recorder.read();
            }
        }).start();
    }
}
