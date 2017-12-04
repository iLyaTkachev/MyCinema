package ilyatkachev.github.com.mycinema.http;

import java.io.InputStream;

public interface IResponseListener {
    void onResponse(InputStream pInputStream) throws Exception;

    void onError(Throwable pThrowable);
}
