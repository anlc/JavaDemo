package other;

public class TestSync {

//    boolean isStarted = false;
//
//    public synchronized void setStart(boolean isStarted){
//        this.isStarted = isStarted;
//        System.out.println("set:" + isStarted);
//        try {
//            Thread.sleep(600);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void start(){
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                setStart(!isStarted);
//            }
//        }).start();
//    }
//
//    public void read(){
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(isStarted);
//            }
//        }).start();
//    }

    Object writeLock = new Object();
    private boolean isRelease = false;

    public void writeData() {
        synchronized (writeLock) {
            System.out.println("write finish1: " + isRelease);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("write finish2: " + isRelease);
        }
        System.out.println("call finish: " + isRelease);
    }

    public void release() {
        synchronized (writeLock) {
            isRelease = true;
            System.out.println("set release: " + isRelease);
        }
    }

    public static void main(String[] args) {
        TestSync testSync = new TestSync();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                testSync.writeData();
//                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testSync.release();
//                }
            }
        }).start();
    }
}
