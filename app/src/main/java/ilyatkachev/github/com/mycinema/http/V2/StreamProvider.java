package ilyatkachev.github.com.mycinema.http.V2;

import java.io.IOException;
import java.io.InputStream;

public interface StreamProvider<T> {

    InputStream get(T path) throws IOException;
}
