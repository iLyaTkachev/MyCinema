package ilyatkachev.github.com.mycinema.util.usecase;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ilyatkachev.github.com.mycinema.util.usecase.UseCaseScheduler;

/**
 * Executes asynchronous tasks using a ThreadPoolExecutor.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

    private final Handler mHandler = new Handler();

    public static final int POOL_SIZE = 2;

    public static final int MAX_POOL_SIZE = 4;

    public static final int TIMEOUT = 30;

    private ThreadPoolExecutor mThreadPoolExecutor;

    public UseCaseThreadPoolScheduler() {
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
    }

    @Override
    public void execute(Runnable pRunnable) {
        mThreadPoolExecutor.execute(pRunnable);
    }

    @Override
    public <R extends UseCase.ResponseValue> void notifyResponse(final R pResponse, final UseCase.UseCaseCallback<R> pUseCaseCallback) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                pUseCaseCallback.onSuccess(pResponse);
            }
        });
    }

    @Override
    public <R extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<R> pUseCaseCallback) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                pUseCaseCallback.onError();
            }
        });
    }
}
