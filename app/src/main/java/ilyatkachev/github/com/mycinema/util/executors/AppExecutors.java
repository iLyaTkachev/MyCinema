package ilyatkachev.github.com.mycinema.util.executors;

import android.support.annotation.VisibleForTesting;

import java.util.concurrent.Executor;

/**
 * Global executor pools for the whole application.
 */
public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private final Executor mNetworkIO;

    private final Executor mDiskIO;

    private final Executor mMainThread;

    public AppExecutors() {
        this(new NetworkIOThreadExecutor(THREAD_COUNT), new DiskIOThreadExecutor(), new MainThreadExecutor());
    }

    @VisibleForTesting
    private AppExecutors(final Executor pNetworkIO, final Executor pDiskIO, final Executor pMainThread) {
        mNetworkIO = pNetworkIO;
        mDiskIO = pDiskIO;
        mMainThread = pMainThread;
    }

    public Executor getNetworkIO() {
        return mNetworkIO;
    }

    public Executor getDiskIO() {
        return mDiskIO;
    }

    public Executor getMainThread() {
        return mMainThread;
    }
}
