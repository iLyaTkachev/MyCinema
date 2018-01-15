package ilyatkachev.github.com.mycinema.util.usecase;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public Q getRequestValues() {
        return mRequestValues;
    }

    public void setRequestValues(final Q pRequestValues) {
        mRequestValues = pRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(final UseCaseCallback<P> pUseCaseCallback) {
        mUseCaseCallback = pUseCaseCallback;
    }

    public void run() {
        executeUseCase(mRequestValues);
    }

    public abstract void executeUseCase(Q requestValues);

    public interface RequestValues {

    }

    public interface ResponseValue {

    }

    public interface UseCaseCallback<R> {

        void onSuccess(R response);

        void onError();
    }
}
