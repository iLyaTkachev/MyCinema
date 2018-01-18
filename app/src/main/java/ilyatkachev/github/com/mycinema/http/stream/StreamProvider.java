package ilyatkachev.github.com.mycinema.http.stream;

import java.io.IOException;

public interface StreamProvider<T,P> {

    P get(T path) throws IOException;
}
