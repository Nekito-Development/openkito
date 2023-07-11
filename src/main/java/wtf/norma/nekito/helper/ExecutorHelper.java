package wtf.norma.nekito.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ExecutorHelper {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private ExecutorHelper() {
    }

    public static void submit(Runnable runnable) {
        EXECUTOR_SERVICE.submit(runnable);
    }
}
