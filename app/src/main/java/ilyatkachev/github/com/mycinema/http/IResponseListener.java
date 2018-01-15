package ilyatkachev.github.com.mycinema.http;

public interface IResponseListener {
    void onResponse(String pResult) throws Exception;

    void onError(Throwable pThrowable);
}
