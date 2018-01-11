package ilyatkachev.github.com.mycinema.util.usecase;

/**
 * Runs UseCases using a UseCaseScheduler.
 */
public class UseCaseHandler<Q extends UseCase.RequestValues, R extends UseCase.ResponseValue> {

    private static UseCaseHandler INSTANCE;

    private final UseCaseScheduler mUseCaseScheduler;

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }

    public UseCaseHandler(UseCaseScheduler pUseCaseScheduler) {
        mUseCaseScheduler = pUseCaseScheduler;
    }

    public void execute(
            final UseCase<Q, R> useCase, Q values, UseCase.UseCaseCallback<R> callback) {
        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(new UseCaseCallbackWrapper(callback, this));
        mUseCaseScheduler.execute(new Runnable() {

            @Override
            public void run() {

                useCase.run();
            }
        });
    }

    private void notifyResponse(final R response, final UseCase.UseCaseCallback<R> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    private void notifyError(final UseCase.UseCaseCallback<R> useCaseCallback) {
        mUseCaseScheduler.onError(useCaseCallback);
    }

    private static final class UseCaseCallbackWrapper<R extends UseCase.ResponseValue> implements
            UseCase.UseCaseCallback<R> {

        private final UseCase.UseCaseCallback<R> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        public UseCaseCallbackWrapper(final UseCase.UseCaseCallback<R> callback, final UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(final R response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError() {
            mUseCaseHandler.notifyError(mCallback);
        }
    }
}
