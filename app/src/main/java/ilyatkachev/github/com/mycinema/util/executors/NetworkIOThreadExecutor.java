package ilyatkachev.github.com.mycinema.util.executors;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NetworkIOThreadExecutor implements Executor {

    private final Executor mNetworkIO;

    public NetworkIOThreadExecutor(int mThreadCount) {
        mNetworkIO = Executors.newFixedThreadPool(mThreadCount);
    }

    @Override
    public void execute(@NonNull Runnable pRunnable) {
        mNetworkIO.execute(pRunnable);
    }
}
