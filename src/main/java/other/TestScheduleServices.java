package other;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduleServices {

    ScheduledExecutorService service;

    public static void main(String[] args) {
        TestScheduleServices services = new TestScheduleServices();
        services.scheduleTask();

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        services.scheduleTask();
    }

    public void scheduleTask() {
        service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(() -> {
            System.out.println(" started ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, 2, TimeUnit.SECONDS);
        service.schedule(this::cancel, 10, TimeUnit.SECONDS);
    }

    public void cancel() {
        if (service != null) {
            System.out.println(" shutdown ");
            service.shutdown();
        }
    }
}
