package ilyatkachev.github.com.mycinema.util.usecase;

public interface UseCaseScheduler {

    void execute(Runnable pRunnable);

    <R extends UseCase.ResponseValue> void notifyResponse(final R pResponse, final UseCase.UseCaseCallback<R> pUseCaseCallback);

    <R extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<R> pUseCaseCallback);
}
