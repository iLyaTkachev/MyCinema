package ilyatkachev.github.com.mycinema.http;

import java.io.InputStream;

public interface IResponseListener {
    void onResponse(InputStream pResult) throws Exception;

    void onError(Throwable pThrowable);
}
