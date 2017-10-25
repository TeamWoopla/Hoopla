import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TheTimer {

    static boolean ShopBTN = true;

    TheTimer(){
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(TheTimer::myTask, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private static void myTask() {
        ShopBTN = true;

    }
}
