package ilyatkachev.github.com.mycinema.util.usecase;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public Q getRequestValues() {
        return mRequestValues;
    }

    public void setRequestValues(Q pRequestValues) {
        mRequestValues = pRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> pUseCaseCallback) {
        mUseCaseCallback = pUseCaseCallback;
    }

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public interface RequestValues {

    }

    public interface ResponseValue {

    }

    public interface UseCaseCallback<R> {

        void onSuccess(R response);

        void onError();
    }
}
