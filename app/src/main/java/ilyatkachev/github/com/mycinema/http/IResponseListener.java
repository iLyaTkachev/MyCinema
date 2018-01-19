package ilyatkachev.github.com.mycinema.http;

import java.io.InputStream;

public interface IResponseListener {
    void onResponse(InputStream pResult);

    void onError(Throwable pThrowable);
}
