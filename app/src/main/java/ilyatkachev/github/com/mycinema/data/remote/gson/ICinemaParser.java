package ilyatkachev.github.com.mycinema.data.remote.gson;

import java.io.InputStream;

public interface ICinemaParser <T> {
    T parse(InputStream pStream, T object);
}
