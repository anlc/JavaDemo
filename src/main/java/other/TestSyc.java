package other;

public class TestSyc {

    private Object audioLock = new Object();
    private Object imageLock = new Object();

    private void encodeAudio() throws InterruptedException {
        synchronized (audioLock){
            System.out.println("编码音频");
            Thread.sleep(500);
            System.out.println("编码音频结束 -----");
        }
    }

    private void encodeImage() throws InterruptedException {
        synchronized (imageLock){
            System.out.println("编码 视频");
            Thread.sleep(500);
            System.out.println("编码 视频结束 ======  ");
        }
    }

    private void close(){
        System.out.println("close call ---- ");
        synchronized (audioLock) {
            System.out.println("音频关闭 ---------------");
        }
        synchronized (imageLock) {
            System.out.println("视频关闭 =============");
        }
        System.out.println("关闭完成");
    }

    static boolean isRelease = false;

    public static void main(String[] args) {
        TestSyc syc = new TestSyc();


        new Thread(() -> {
            while (!isRelease) {
                try {
                    syc.encodeAudio();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            while (!isRelease) {
                try {
                    syc.encodeImage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRelease = true;
        syc.close();
    }
}
