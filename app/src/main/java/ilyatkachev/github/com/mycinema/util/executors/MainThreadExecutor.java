package ilyatkachev.github.com.mycinema.util.executors;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull final Runnable pRunnable) {
        mainThreadHandler.post(pRunnable);
    }
}
