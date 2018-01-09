package ilyatkachev.github.com.mycinema.http;

public interface IHttpClient {

    void request(String url, IResponseListener listener);
}
