package other;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(" ------- ");
            }
        };
        timer.schedule(timerTask, 0, 1000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();
        System.out.println("------------ cancel -------------");
    }
}
