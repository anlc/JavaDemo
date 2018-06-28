package thread_demo;

public class ThreadCallBack {
    public static void main(String[] args) {
        new Thread("001") {
            @Override
            public void run() {
                System.out.println(" start :" + Thread.currentThread().getName());
                new ThreadDemo(new CallBack() {
                    @Override
                    public void callBack() {
                        System.out.println(" call back :" + Thread.currentThread().getName());
                    }
                });
            }
        }.start();
    }


    interface CallBack {
        void callBack();
    }

    static class ThreadDemo {
        public ThreadDemo(final CallBack callBack) {
            System.out.println(" new class :" + Thread.currentThread().getName());
            new Thread("002") {
                @Override
                public void run() {
                    try {
                        System.out.println(" run ing :" + Thread.currentThread().getName());
                        Thread.sleep(100);
                        callBack.callBack();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
