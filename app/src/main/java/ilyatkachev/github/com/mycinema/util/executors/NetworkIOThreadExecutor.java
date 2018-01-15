package ilyatkachev.github.com.mycinema.util.executors;

import android.support.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NetworkIOThreadExecutor implements Executor {

    private final Executor mNetworkIO;

    public NetworkIOThreadExecutor(int mThreadCount) {
        mNetworkIO = new ThreadPoolExecutor(mThreadCount,mThreadCount,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void execute(@NonNull Runnable pRunnable) {
        mNetworkIO.execute(pRunnable);
    }
}
