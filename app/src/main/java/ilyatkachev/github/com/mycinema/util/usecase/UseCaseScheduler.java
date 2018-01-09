package ilyatkachev.github.com.mycinema.util.usecase;

public interface UseCaseScheduler {

    void execute(Runnable pRunnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V pResponse, final UseCase.UseCaseCallback<V> pUseCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<V> pUseCaseCallback);
}
